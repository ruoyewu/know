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

    private int type;
    private String items;
    private int reviewNum;
    private int failNum;
    private long lastReview;
    private long createTime;
    private long updateTime;

    public RecordTable(int type, String items) {
        this.type = type;
        this.items = items;
    }

    public RecordTable(int type, String items, int reviewNum, int failNum,
                       long lastReview, long createTime, long updateTime) {
        this(type, items);
        this.reviewNum = reviewNum;
        this.failNum = failNum;
        this.lastReview = lastReview;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }

    public long getLastReview() {
        return lastReview;
    }

    public void setLastReview(long lastReview) {
        this.lastReview = lastReview;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
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
}
