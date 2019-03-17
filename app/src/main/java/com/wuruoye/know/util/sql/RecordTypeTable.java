package com.wuruoye.know.util.sql;

import android.database.sqlite.SQLiteDatabase;

public class RecordTypeTable implements Table {
    public static final String NAME = "record_type";
    public static final String TITLE = "title";
    public static final String ITEMS = "items";

    private String title;
    private String items;

    private RecordTypeTable(String title, String items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    static void create(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + " (" +
                "id integer primary key autoincrement, " +
                TITLE + " text, " +
                ITEMS + " items " +
                ")");
    }
}
