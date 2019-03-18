package com.wuruoye.know.util.sql.table;

import android.database.sqlite.SQLiteDatabase;

public class RecordTable implements Table {
    public static final String NAME = "record";
    public static final String TYPE = "type";
    public static final String ITEMS = "items";
    public static final String REVIEW_NUM = "review_num";
    public static final String FAIL_NUM = "fail_num";
    public static final String LAST_REVIEW = "last_review";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

    private int id;
    private int type;
    private String items;
    private int reviewNum;
    private int failNum;
    private long lastReview;
    private long createTime;
    private long updateTime;

    public RecordTable(int id, int type, String items, int reviewNum, int failNum,
                       long lastReview, long createTime, long updateTime) {
        this.id = id;
        this.type = type;
        this.items = items;
        this.reviewNum = reviewNum;
        this.failNum = failNum;
        this.lastReview = lastReview;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + " (" +
                "id integer primary key autoincrement, " +
                TYPE + " integer, " +
                ITEMS + " text, " +
                REVIEW_NUM + " integer, " +
                FAIL_NUM + " integer, " +
                LAST_REVIEW + " integer, " +
                CREATE_TIME + " integer, " +
                UPDATE_TIME + " integer" +
                ")");
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
}
