package com.wuruoye.know.util.sql.table;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecordTypeTable implements Table {
    public static final String NAME = "record_type";
    public static final String TITLE = "title";
    public static final String ITEMS = "items";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

    private int id;
    private String title;
    private String items;
    private long createTime;
    private long updateTime;

    public RecordTypeTable(int id, String title, String items, long createTime, long updateTime) {
        this.id = id;
        this.title = title;
        this.items = items;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + " (" +
                "id integer primary key autoincrement, " +
                TITLE + " text, " +
                ITEMS + " text, " +
                CREATE_TIME + " integer, " +
                UPDATE_TIME + " integer " +
                ")");
    }

    public static List<RecordTypeTable> queryAll(SQLiteDatabase db) {
        List<RecordTypeTable> result = new ArrayList<>();
        Cursor cursor = db.query(NAME, null, null,
                null, null, null, CREATE_TIME);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String items = cursor.getString(2);
                long createTime = cursor.getLong(3);
                long updateTime = cursor.getLong(4);
                result.add(new RecordTypeTable(id, title, items, createTime, updateTime));
            }
        } finally {
            cursor.close();
        }

        return result;
    }

    @Override
    public boolean save(SQLiteDatabase db) {
        return false;
    }

    @Override
    public boolean delete(SQLiteDatabase db) {
        return false;
    }

    @Override
    public boolean update(SQLiteDatabase db) {
        return false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getItems() {
        return items;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }
}
