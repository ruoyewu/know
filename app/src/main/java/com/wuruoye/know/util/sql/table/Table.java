package com.wuruoye.know.util.sql.table;

import android.database.sqlite.SQLiteDatabase;

public interface Table {
    boolean save(SQLiteDatabase db);
    boolean delete(SQLiteDatabase db);
    boolean update(SQLiteDatabase db);
}
