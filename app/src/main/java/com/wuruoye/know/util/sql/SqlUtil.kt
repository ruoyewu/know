package com.wuruoye.know.util.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.support.design.widget.TextInputLayout
import android.view.View
import android.view.ViewGroup
import com.wuruoye.know.model.beans.*
import com.wuruoye.know.util.sql.SqlUtil.ViewTableItem.Companion.LAYOUT_VIEW
import com.wuruoye.know.util.sql.SqlUtil.ViewTableItem.Companion.TEXT_VIEW
import com.wuruoye.know.util.sql.table.*

/**
 * Created at 2019/3/18 12:21 by wuruoye
 * Description: 负责数据库数据与应用数据的转换
 */
class SqlUtil private constructor(context: Context) {

    private val sh: SqliteHelper = SqliteHelper(context)


    // record type
    fun queryRecordTypeWithoutItems(): List<RecordType> {
        val result = ArrayList<RecordType>()
        val db = sh.readableDatabase
        try {
            val typeTables = RecordTypeTable.queryAll(db)

            for (table in typeTables) {
                result.add(RecordType(table.id, table.title, arrayListOf(),
                        table.createTime, table.updateTime))
            }
        } finally {
            db.close()
        }
        return result
    }

    fun saveRecordType(recordType: RecordType): Boolean {
        val db = sh.writableDatabase
        try {
            db.beginTransaction()
            // save record view
            val builder = StringBuilder("[")
            val views = recordType.views
            for (view in views) {
                val item = generateTable(view, db)
                val table = item.table
                val id = if (view.id >= 0) {
                    table.update(db)
                    view.id
                } else {
                    table.save(db)
                }
                view.id = id
                builder.append(item.type).append(',').append(id).append(',')
            }
            if (builder.length > 1) builder[builder.length-1] = ']'
            else builder.append(']')

            // save record type
            val table = RecordTypeTable(recordType, builder.toString())
            if (table.id >= 0) table.update(db)
            else table.save(db)

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
            db.close()
        }
        return true
    }

    fun queryRecordType(id: Int): RecordType {
        sh.readableDatabase.use {
            val table = RecordTypeTable.query(it, id)
            val views = ArrayList<RecordView>()
            val items = table.items.subSequence(1, table.items.length-1)
                    .split(',')
            if (items.size > 1) {
                var pos = 0
                while (pos < items.size) {
                    val type = items[pos++].toInt()
                    val tableId = items[pos++].toInt()
                    views.add(findRecordView(type, tableId, it))
                }
            }

            return RecordType(table, views)
        }
    }


    // record
    fun queryRecord(id: Int): Record {
        sh.readableDatabase.use {
            return RecordTable.query(it, id)
        }
    }

    fun queryRecordWithType(type: Int): List<Record> {
        sh.readableDatabase.use {
            return RecordTable.queryAll(it, type)
        }
    }

    fun queryRecordWithTypeTime(type: Int, timeLimit: Long): List<Record> {
        sh.readableDatabase.use {
            return RecordTable.queryWithTypeTime(it, type, timeLimit)
        }
    }

    fun saveRecord(record: Record): Boolean {
        sh.writableDatabase.use {
            val table = RecordTable(record)
            return if (table.id >= 0) {
                table.update(it) > 0
            } else {
                table.save(it) >= 0
            }
        }
    }

    fun deleteRecord(id: Int): Boolean {
        sh.writableDatabase.use {
            return RecordTable.delete(it, id)
        }
    }

    fun queryRecordItem(recordId: Int, type: Int, typeId: Int): RecordItem? {
        sh.readableDatabase.use {
            return RecordItemTable.query(it, recordId, type, typeId)
        }
    }

    fun saveRecordWithItems(record: Record, recordType: RecordType, view: View): Boolean {
        val db = sh.writableDatabase
        try {
            db.beginTransaction()

            val table = RecordTable(record)
            val id = if (table.id < 0) {
                table.save(db)
            } else {
                table.update(db)
                table.id
            }

            val views = recordType.views
            val parent = view as ViewGroup

            saveRecordItem(id, views, parent, db)

            db.setTransactionSuccessful()
            return true
        } catch (ignore: Exception) {
            return false
        } finally {
            db.endTransaction()
            db.close()
        }
    }

    private fun saveRecordItem(recordId: Int, views: ArrayList<RecordView>, parent: ViewGroup,
                               db: SQLiteDatabase) {
        for (i in 0 until views.size) {
            val v = views[i]
            val child = parent.getChildAt(i)
            if (v is RecordTextView && v.isEditable) {
                val item = child.tag
                val itemTable = if (item != null) {
                    RecordItemTable(item as RecordItem)
                } else {
                    RecordItemTable(-1, recordId, ViewTableItem.TEXT_VIEW, v.id, "")
                }
                itemTable.content = (child as TextInputLayout).editText!!.text.toString()
                itemTable.save(db)
            } else if (v is RecordLayoutView) {
                saveRecordItem(recordId, v.views, child as ViewGroup, db)
            }
        }
    }

    // util
    private fun generateTable(view: RecordView, db: SQLiteDatabase): ViewTableItem {
        if (view is RecordLayoutView) {
            val views = view.views
            val builder = StringBuilder("[")
            for (v in views) {
                val item = generateTable(v, db)
                val table = item.table
                val id = if (v.id >= 0) {
                    table.update(db)
                    v.id
                } else {
                    table.save(db)
                }
                v.id = id
                builder.append(item.type).append(",").append(id).append(",")
            }
            if (builder.length > 1) builder[builder.length-1] = ']'
            else builder.append(']')

            return ViewTableItem(LAYOUT_VIEW, LayoutViewTable(view, builder.toString()))
        }

        return ViewTableItem(TEXT_VIEW, TextViewTable(view as RecordTextView))
    }

    private fun findRecordView(type: Int, id: Int, db: SQLiteDatabase): RecordView {
        if (type == LAYOUT_VIEW) {
            val table = LayoutViewTable.query(db, id)
            val views = ArrayList<RecordView>()
            val items = table.items.subSequence(1, table.items.length-1).split(",")
            if (items.size > 1) {
                var pos = 0
                while (pos < items.size) {
                    val ty = items[pos++].toInt()
                    val tableId = items[pos++].toInt()
                    views.add(findRecordView(ty, tableId, db))
                }
            }
            return RecordLayoutView(table, views)
        }

        return RecordTextView(TextViewTable.query(db, id)!!)
    }

    private fun findViewTableByType(type: Int, id: Int, db: SQLiteDatabase): ViewTable {
        return TextViewTable.query(db, id)!!
    }

    class ViewTableItem (
            val type: Int,
            val table: ViewTable
    ) {
        companion object {
            val TEXT_VIEW = 1
            val LAYOUT_VIEW = 2
        }
    }

    companion object {
        @Volatile
        private var sInstance: SqlUtil? = null

        fun getInstance(context: Context): SqlUtil {
            if (sInstance == null) {
                synchronized(SqlUtil::class.java) {
                    if (sInstance == null) {
                        sInstance = SqlUtil(context)
                    }
                }
            }
            return sInstance!!
        }

        fun lastInsertionId(db: SQLiteDatabase, tableName: String): Int {
            val cursor = db.rawQuery("select last_insert_rowid_from $tableName",
                    null)
            val id = if (cursor.moveToFirst()) cursor.getInt(0) else -1;
            cursor.close()
            return id
        }
    }
}
