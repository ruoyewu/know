package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.RecordType
import java.util.*

class RecordTypeTable(id: Int,
                      val title: String,
                      val items: String,
                      val createTime: Long,
                      val updateTime: Long) : Table(id) {

    constructor(recordType: RecordType, items: String): this(recordType.id, recordType.title,
            items, recordType.createTime, recordType.updateTime)

    override fun save(db: SQLiteDatabase): Int {
        id = db.insert(NAME, null, contentValues()).toInt()
        return id
    }

    override fun delete(db: SQLiteDatabase): Int {
        return db.delete(NAME, "id=?", arrayOf(id.toString()))
    }

    override fun update(db: SQLiteDatabase): Int {
        return db.update(NAME, contentValues(), "id=?", arrayOf(id.toString()))
    }

    override fun contentValues(): ContentValues {
        val values = ContentValues()
        values.put(TITLE, title)
        values.put(ITEMS, items)
        values.put(CREATE_TIME, createTime)
        values.put(UPDATE_TIME, updateTime)
        return values
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
            val cursor = db.query(NAME, null, null, null,
                    null, null, CREATE_TIME)

            try {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    val id = cursor.getInt(0)
                    val title = cursor.getString(1)
                    val items = cursor.getString(2)
                    val createTime = cursor.getLong(3)
                    val updateTime = cursor.getLong(4)
                    result.add(RecordTypeTable(id, title, items, createTime, updateTime))
                    cursor.moveToNext()
                }
            } finally {
                cursor.close()
            }

            return result
        }
    }
}
