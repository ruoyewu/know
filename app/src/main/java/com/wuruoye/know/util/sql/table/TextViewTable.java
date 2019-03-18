package com.wuruoye.know.util.sql.table;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.InputType;
import android.view.Gravity;

public class TextViewTable implements ViewTable {
    public static final String NAME = "text_view";
    public static final String TEXT = "text";
    public static final String TEXT_SIZE = "text_size";
    public static final String TEXT_COLOR = "text_color";
    public static final String HINT = "hint";
    public static final String HINT_SIZE = "hint_size";
    public static final String HINT_COLOR = "hint_color";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String BG_COLOR = "bg_color";
    public static final String FG_COLOR = "fg_color";
    public static final String MARGIN_LEFT = "margin_left";
    public static final String MARGIN_RIGHT = "margin_right";
    public static final String MARGIN_TOP = "margin_top";
    public static final String MARGIN_BOTTOM = "margin_bottom";
    public static final String PADDING_LEFT = "padding_left";
    public static final String PADDING_RIGHT = "padding_right";
    public static final String PADDING_TOP = "padding_top";
    public static final String PADDING_BOTTOM = "padding_bottom";
    public static final String GRAVITY = "gravity";
    public static final String TEXT_STYLE = "text_style";
    public static final String INPUT_TYPE = "input_type";
    public static final String EDITABLE = "editable";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

    private int id;
    private String text;
    private int textSize;
    private int textColor;
    private String hint;
    private int hintSize;
    private int hintColor;
    private int width;
    private int height;
    private int bgColor;
    private int fgColor;
    private int marginTop;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;
    private int paddingTop;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int gravity;
    private int textStyle;
    private int inputType;
    private boolean editable;
    private long createTime;
    private long updateTime;

    public TextViewTable(int id, String text, int textSize, int textColor, String hint,
                         int hintSize, int hintColor, int width, int height,
                         int bgColor, int fgColor, int marginTop, int marginBottom,
                         int marginLeft, int marginRight, int paddingTop,
                         int paddingBottom, int paddingLeft, int paddingRight,
                         int gravity, int textStyle, int inputType, boolean editable,
                         long createTime, long updateTime) {
        this.id = id;
        this.text = text;
        this.textSize = textSize;
        this.textColor = textColor;
        this.hint = hint;
        this.hintSize = hintSize;
        this.hintColor = hintColor;
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.paddingTop = paddingTop;
        this.paddingBottom = paddingBottom;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.gravity = gravity;
        this.textStyle = textStyle;
        this.inputType = inputType;
        this.editable = editable;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static void create(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + " (" +
                "id integer primary key autoincrement, " +
                TEXT + " text, " +
                TEXT_SIZE + " integer, " +
                TEXT_COLOR + " integer, " +
                HINT + " text, " +
                HINT_COLOR + " integer, " +
                HINT_SIZE + " integer, " +
                WIDTH + " integer, " +
                HEIGHT + " integer, " +
                BG_COLOR + " integer, " +
                FG_COLOR + " integer, " +
                MARGIN_TOP + " integer, " +
                MARGIN_BOTTOM + " integer, " +
                MARGIN_LEFT + " integer, " +
                MARGIN_RIGHT + " integer, " +
                PADDING_TOP + " integer, " +
                PADDING_BOTTOM + " integer, " +
                PADDING_LEFT + " integer, " +
                PADDING_RIGHT + " integer, " +
                GRAVITY + " integer, " +
                TEXT_STYLE + " integer, " +
                INPUT_TYPE + " integer, " +
                EDITABLE + " integer ," +
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
