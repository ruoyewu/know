package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.Record

class RecordTable(id: Int,
                  private val type: Int,
                  private val reviewNum: Int,
                  private val failNum: Int,
                  private val lastReview: Long,
                  private val createTime: Long,
                  private val updateTime: Long) : Table(id) {
    constructor(record: Record): this(record.id, record.type, record.reviewNum,
            record.failNum, record.lastReview, record.createTime, record.updateTime)

    override fun save(db: SQLiteDatabase): Int {
        return if (id < 0) {
            db.insert(NAME, null, contentValues()).toInt()
        } else {
            db.update(NAME, contentValues(), "id=?", arrayOf(id.toString()))
            id
        }
    }

    override fun delete(db: SQLiteDatabase): Int {
        return db.delete(NAME, "id=?", arrayOf(id.toString()))
    }

    override fun contentValues(): ContentValues {
        val values = ContentValues()
        values.put(TYPE, type)
        values.put(REVIEW_NUM, reviewNum)
        values.put(FAIL_NUM, failNum)
        values.put(LAST_REVIEW, lastReview)
        values.put(CREATE_TIME, createTime)
        values.put(UPDATE_TIME, updateTime)
        return values
    }

    companion object {
        val NAME = "record"
        val TYPE = "type"
        val ITEMS = "items"
        val REVIEW_NUM = "review_num"
        val FAIL_NUM = "fail_num"
        val LAST_REVIEW = "last_review"
        val CREATE_TIME = "create_time"
        val UPDATE_TIME = "update_time"
        val DESC = " desc"

        fun create(db: SQLiteDatabase) {
            db.execSQL("create table " + NAME + " (" +
                    "id integer primary key autoincrement, " +
                    TYPE + " integer, " +
                    REVIEW_NUM + " integer, " +
                    FAIL_NUM + " integer, " +
                    LAST_REVIEW + " integer, " +
                    CREATE_TIME + " integer, " +
                    UPDATE_TIME + " integer" +
                    ")")
        }

        fun delete(db: SQLiteDatabase, id: Int): Boolean {
            return db.delete(NAME, "id=?", arrayOf(id.toString())) != 0
        }

        fun query(db: SQLiteDatabase, id: Int): Record {
            val cursor = db.query(NAME, null, "id=?",
                    arrayOf(id.toString()), null, null, CREATE_TIME)

            cursor.use { c ->
                c.moveToFirst()
                return fromCursor(c)
            }
        }

        fun queryAll(db: SQLiteDatabase, type: Int): List<Record> {
            val cursor = db.query(NAME, null, "type=?",
                    arrayOf(type.toString()), null, null, CREATE_TIME + DESC)

            val result = ArrayList<Record>()
            cursor.use { c ->
                c.moveToFirst()
                while (!c.isAfterLast) {
                    result.add(fromCursor(c))
                    c.moveToNext()
                }
            }

            return result
        }

        fun queryWithTypeTime(db: SQLiteDatabase, type: Int, timeLimit: Long): List<Record> {
            val selection = StringBuilder()
            if (type >= 0) selection.append("type=? and ")
            selection.append("create_time>?")

            val cursor = db.query(NAME, null, selection.toString(),
                    if (type >= 0) arrayOf(type.toString(), timeLimit.toString())
                    else arrayOf(timeLimit.toString()), null,
                    null, CREATE_TIME + DESC)

            val result = ArrayList<Record>()
            cursor.use {
                it.moveToFirst()
                while (!it.isAfterLast) {
                    result.add(fromCursor(it))
                    it.moveToNext()
                }
            }
            return result
        }

        private fun fromCursor(cursor: Cursor): Record {
            val id = cursor.getInt(0)
            val type = cursor.getInt(1)
            val reviewNum = cursor.getInt(2)
            val failNum = cursor.getInt(3)
            val lastReview = cursor.getLong(4)
            val createTime = cursor.getLong(5)
            val updateTime = cursor.getLong(6)
            return Record(id, type, reviewNum, failNum,
                    lastReview, createTime, updateTime)
        }
    }
}
