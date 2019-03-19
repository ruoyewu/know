package com.wuruoye.know.util.sql.table

import android.database.sqlite.SQLiteDatabase

/**
 * Created at 2019/3/17 22:24 by wuruoye
 * Description:
 */
class RecordTypeItemTable(private val id: Int,
                          private val viewType: Int,
                          private val viewId: Int) : Table {

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
        val NAME = "record_type_item"
        val VIEW_TYPE = "view_type"
        val VIEW_ID = "view_id"

        fun create(db: SQLiteDatabase) {
            db.execSQL("create table " + NAME + " (" +
                    "id integer primary key autoincrement, " +
                    VIEW_TYPE + " integer, " +
                    VIEW_ID + " integer " +
                    ")")
        }
    }
}
