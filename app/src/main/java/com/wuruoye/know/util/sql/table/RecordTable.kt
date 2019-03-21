package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class RecordTable(id: Int,
                  private val type: Int,
                  private val items: String,
                  private val reviewNum: Int,
                  private val failNum: Int,
                  private val lastReview: Long,
                  private val createTime: Long,
                  private val updateTime: Long) : Table(id) {

    override fun save(db: SQLiteDatabase): Int {
        return -1
    }

    override fun delete(db: SQLiteDatabase): Int {
        return -1
    }

    override fun update(db: SQLiteDatabase): Int {
        return -1
    }

    override fun contentValues(): ContentValues {
        val values = ContentValues()
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
    }
}
