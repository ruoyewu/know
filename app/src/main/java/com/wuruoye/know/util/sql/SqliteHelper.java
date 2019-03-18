package com.wuruoye.know.util.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wuruoye.know.util.sql.table.RecordTable;
import com.wuruoye.know.util.sql.table.RecordTypeItemTable;
import com.wuruoye.know.util.sql.table.RecordTypeTable;
import com.wuruoye.know.util.sql.table.TextViewTable;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DB_NAME = "con.wuruoye.know.db";

    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TextViewTable.create(db);
        RecordTypeTable.create(db);
        RecordTable.create(db);
        RecordTypeItemTable.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
