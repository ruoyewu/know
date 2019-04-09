package com.wuruoye.know.util.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.*
import com.wuruoye.know.util.sql.SqlUtil.ViewTableItem.Companion.IMAGE_VIEW
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
                val id = table.save(db)
                view.id = id
                builder.append(item.type).append(',').append(id).append(',')
            }
            if (builder.length > 1) builder[builder.length-1] = ']'
            else builder.append(']')

            // save record type
            val table = RecordTypeTable(recordType, builder.toString())
            table.save(db)

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

    fun deleteRecordTypeItem(type: Int, typeId: Int) {
        sh.writableDatabase.use {
            when (type) {
                ViewTableItem.TEXT_VIEW -> {
                    TextViewTable.delete(it, typeId)
                }
                ViewTableItem.LAYOUT_VIEW -> {
                    LayoutViewTable.delete(it, typeId)
                }
            }
            RecordItemTable.delete(it, type, typeId)
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
            return table.save(it) >= 0
        }
    }

    fun saveRecordWithItems(record: Record, items: ArrayList<RecordItem>): Boolean {
        sh.writableDatabase.use {
            it.beginTransaction()
            try {
                val table = RecordTable(record)
                val id = table.save(it)
                if (id < 0) {
                    return false
                }
                for (item in items) {
                    item.recordId = id
                    val itemTable = RecordItemTable(item)
                    itemTable.save(it)
                }
                it.setTransactionSuccessful()
                return true
            } finally {
                it.endTransaction()
            }
        }
    }

    fun queryRecordItemsWithRecord(recordId: Int): ArrayList<RecordItem> {
        sh.readableDatabase.use {
            return RecordItemTable.queryByRecord(it, recordId)
        }
    }

    fun deleteRecord(id: Int): Boolean {
        sh.writableDatabase.use {
            if (RecordTable.delete(it, id)) {
                RecordItemTable.delete(it, id)
                return true
            }
            return false
        }
    }

    fun queryRecordItem(recordId: Int, type: Int, typeId: Int): RecordItem? {
        sh.readableDatabase.use {
            return RecordItemTable.query(it, recordId, type, typeId)
        }
    }

    fun queryRecordItem(recordId: Int, type: Int): RecordItem? {
        sh.readableDatabase.use {
            return RecordItemTable.query(it, recordId, type)
        }
    }

    private fun generateTable(view: RecordView, db: SQLiteDatabase): ViewTableItem {
        if (view is RecordLayoutView) {
            val views = view.views
            val builder = StringBuilder("[")
            for (v in views) {
                val item = generateTable(v, db)
                val id = item.table.save(db)
                v.id = id
                builder.append(item.type).append(",").append(id).append(",")
            }
            if (builder.length > 1) builder[builder.length-1] = ']'
            else builder.append(']')

            return ViewTableItem(LAYOUT_VIEW, LayoutViewTable(view, builder.toString()))
        } else if (view is RecordImageView) {
            return ViewTableItem(IMAGE_VIEW, ImageViewTable(view))
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
        } else if (type == IMAGE_VIEW) {
            return ImageViewTable.query(db, id)!!
        }

        return TextViewTable.query(db, id)!!
    }

    class ViewTableItem (
            val type: Int,
            val table: ViewTable
    ) {
        companion object {
            val TEXT_VIEW = 1
            val LAYOUT_VIEW = 2
            val IMAGE_VIEW = 3

            fun getType(view: RecordView): Int {
                return when (view) {
                    is RecordLayoutView -> LAYOUT_VIEW
                    is RecordTextView -> TEXT_VIEW
                    is RecordImageView -> IMAGE_VIEW
                    else -> -1
                }
            }
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
    }
}
