package com.wuruoye.know.util.sql.table

import android.database.sqlite.SQLiteDatabase
import java.util.*

class RecordTypeTable(val id: Int,
                      val title: String,
                      val items: String,
                      val createTime: Long,
                      val updateTime: Long) : Table {

    override fun save(db: SQLiteDatabase): Boolean {
        return false
    }

    override fun delete(db: SQLiteDatabase): Boolean {
        return false
    }

    override fun update(db: SQLiteDatabase): Boolean {
        return false
    }

    companion object {
        val NAME = "record_type"
        val TITLE = "title"
        val ITEMS = "items"
        val CREATE_TIME = "create_time"
        val UPDATE_TIME = "update_time"

        fun create(db: SQLiteDatabase) {
            db.execSQL("create table " + NAME + " (" +
                    "id integer primary key autoincrement, " +
                    TITLE + " text, " +
                    ITEMS + " text, " +
                    CREATE_TIME + " integer, " +
                    UPDATE_TIME + " integer " +
                    ")")
        }

        fun queryAll(db: SQLiteDatabase): List<RecordTypeTable> {
            val result = ArrayList<RecordTypeTable>()
            val cursor = db.query(NAME, null, null, null, null, null, CREATE_TIME)

            try {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    val id = cursor.getInt(0)
                    val title = cursor.getString(1)
                    val items = cursor.getString(2)
                    val createTime = cursor.getLong(3)
                    val updateTime = cursor.getLong(4)
                    result.add(RecordTypeTable(id, title, items, createTime, updateTime))
                }
            } finally {
                cursor.close()
            }

            return result
        }
    }
}
