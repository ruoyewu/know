package com.wuruoye.know.util.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.Record
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.util.sql.SqlUtil.ViewTableItem.Companion.TEXT_VIEW
import com.wuruoye.know.util.sql.table.RecordTable
import com.wuruoye.know.util.sql.table.RecordTypeTable
import com.wuruoye.know.util.sql.table.TextViewTable
import com.wuruoye.know.util.sql.table.ViewTable

/**
 * Created at 2019/3/18 12:21 by wuruoye
 * Description: 负责数据库数据与应用数据的转换
 */
class SqlUtil private constructor(context: Context) {

    private val sh: SqliteHelper = SqliteHelper(context)

    fun getRecordTypeWithoutItems(): List<RecordType> {
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
        db.beginTransaction()
        try {
            // save record view
            val builder = StringBuilder("[")
            val views = recordType.views
            for (view in views) {
                val item = generateTable(view)
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

    private fun generateTable(view: RecordView): ViewTableItem {
//        if (view is RecordTextView) {
            return ViewTableItem(TEXT_VIEW, TextViewTable(view as RecordTextView))
//        }
    }

    private fun findRecordView(type: Int, id: Int, db: SQLiteDatabase): RecordView {
        if (type == TEXT_VIEW) {
            return RecordTextView(TextViewTable.query(db, id)!!)
        } else {
            return RecordTextView(TextViewTable.query(db, id)!!)
        }
    }

    private fun findViewTableByType(type: Int, id: Int, db: SQLiteDatabase): ViewTable {
        if (type == TEXT_VIEW) {
            return TextViewTable.query(db, id)!!
        } else {
            return TextViewTable.query(db, id)!!
        }
    }

    private class ViewTableItem (
            val type: Int,
            val table: ViewTable
    ) {
        companion object {
            val TEXT_VIEW = 1
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
