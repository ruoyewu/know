package com.wuruoye.know.util.sql.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created at 2019/3/17 22:24 by wuruoye
 * Description:
 */
public class RecordTypeItemTable implements Table {
    public static final String NAME = "record_type_item";
    public static final String VIEW_TYPE = "view_type";
    public static final String VIEW_ID = "view_id";

    private int id;
    private int viewType;
    private int viewId;

    public RecordTypeItemTable(int id, int viewType, int viewId) {
        this.id = id;
        this.viewType = viewType;
        this.viewId = viewId;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + " (" +
                "id integer primary key autoincrement, " +
                VIEW_TYPE + " integer, " +
                VIEW_ID + " integer " +
                ")");
    }
}
