package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

abstract class Table(
        var id: Int = -1
) {
    abstract fun save(db: SQLiteDatabase): Int
    abstract fun delete(db: SQLiteDatabase): Int
    abstract fun contentValues(): ContentValues
}
