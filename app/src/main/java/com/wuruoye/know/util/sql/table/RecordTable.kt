package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.Record
import com.wuruoye.know.util.CodeUtil

class RecordTable(id: Int,
                  private val type: Int,
                  private var items: String,
                  private val reviewNum: Int,
                  private val failNum: Int,
                  private val lastReview: Long,
                  private val createTime: Long,
                  private val updateTime: Long) : Table(id) {
    constructor(record: Record): this(record.id, record.type, "", record.reviewNum,
            record.failNum, record.lastReview, record.createTime, record.updateTime){
        val builder = StringBuilder()
        for (item in record.items) {
            builder.append(CodeUtil.encodeBase64(item)).append(",")
        }
        this.items = builder.toString()
    }

    override fun save(db: SQLiteDatabase): Int {
        return db.insert(NAME, null, contentValues()).toInt()
    }

    override fun delete(db: SQLiteDatabase): Int {
        return db.delete(NAME, "id=?", arrayOf(id.toString()))
    }

    override fun update(db: SQLiteDatabase): Int {
        return db.update(NAME, contentValues(), "id=?", arrayOf(id.toString()))
    }

    override fun contentValues(): ContentValues {
        val values = ContentValues()
        values.put(TYPE, type)
        values.put(ITEMS, items)
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

        fun create(db: SQLiteDatabase) {
            db.execSQL("create table " + NAME + " (" +
                    "id integer primary key autoincrement, " +
                    TYPE + " integer, " +
                    ITEMS + " text, " +
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
                    arrayOf(type.toString()), null, null, CREATE_TIME)

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
                    null, CREATE_TIME)

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
            val items = cursor.getString(2)
            val reviewNum = cursor.getInt(3)
            val failNum = cursor.getInt(4)
            val lastReview = cursor.getLong(5)
            val createTime = cursor.getLong(6)
            val updateTime = cursor.getLong(7)
            val itemArray = items.split(",")
            val itemList = ArrayList<String>()
            for (item in itemArray) {
                if (!item.isEmpty()) {
                    itemList.add(CodeUtil.decodeBase64(item))
                }
            }
            return Record(id, type, itemList, reviewNum, failNum,
                    lastReview, createTime, updateTime)
        }
    }
}
