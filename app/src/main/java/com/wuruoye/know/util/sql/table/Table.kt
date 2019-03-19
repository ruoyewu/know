package com.wuruoye.know.util.sql.table

import android.database.sqlite.SQLiteDatabase

interface Table {
    fun save(db: SQLiteDatabase): Boolean
    fun delete(db: SQLiteDatabase): Boolean
    fun update(db: SQLiteDatabase): Boolean
}
