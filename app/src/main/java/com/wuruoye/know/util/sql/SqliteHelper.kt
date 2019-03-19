package com.wuruoye.know.util.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.wuruoye.know.util.sql.table.RecordTable
import com.wuruoye.know.util.sql.table.RecordTypeItemTable
import com.wuruoye.know.util.sql.table.RecordTypeTable
import com.wuruoye.know.util.sql.table.TextViewTable

class SqliteHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        TextViewTable.create(db)
        RecordTypeTable.create(db)
        RecordTable.create(db)
        RecordTypeItemTable.create(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        val VERSION = 1
        val DB_NAME = "con.wuruoye.know.db"
    }
}
