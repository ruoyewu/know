package com.wuruoye.know.util.sql;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;

import com.wuruoye.library.contract.WIView;

public class TextViewTable implements Table {
    public static final String NAME = "text_view";
    public static final String TEXT = "text";
    public static final String TEXT_SIZE = "text_size";
    public static final String TEXT_COLOR = "text_color";
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
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

    public static class Builder {
        private String text;
        private int textSize;
        private int textColor;
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
        private long createTime;
        private long updateTime;


        public Builder() {
            text = "";
            textSize = 15;
            textColor = Color.BLACK;
            width = -1;
            height = -2;
            bgColor = 0;
            fgColor = 0;
            marginTop = marginBottom = marginLeft = marginRight = 0;
            paddingTop = paddingBottom = paddingLeft = paddingRight = 0;
            gravity = Gravity.START;
            textStyle = Typeface.NORMAL;
            createTime = System.currentTimeMillis();
            updateTime = System.currentTimeMillis();
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder textColor(int color) {
            textColor = color;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder bgColor(int color) {
            bgColor = color;
            return this;
        }

        public Builder fgColor(int color) {
            fgColor = color;
            return this;
        }

        public Builder marginTop(int margin) {
            marginTop = margin;
            return this;
        }

        public Builder marginBottom(int margin) {
            marginBottom = margin;
            return this;
        }

        public Builder marginLeft(int margin) {
            marginLeft = margin;
            return this;
        }

        public Builder marginRight(int margin) {
            marginRight = margin;
            return this;
        }

        public Builder paddingLeft(int padding) {
            paddingLeft = padding;
            return this;
        }

        public Builder paddingRight(int padding) {
            paddingRight = padding;
            return this;
        }

        public Builder paddingTop(int padding) {
            paddingTop = padding;
            return this;
        }

        public Builder paddingBottom(int padding) {
            paddingBottom = padding;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder textStyle(int style) {
            textStyle = style;
            return this;
        }

        public Builder createTime(long time) {
            createTime = time;
            return this;
        }

        public TextViewTable build() {
            TextViewTable table = new TextViewTable();
            table.text = text;
            table.textSize = textSize;
            table.textColor = textColor;
            table.bgColor = bgColor;
            table.fgColor = fgColor;
            table.width = width;
            table.height = height;
            table.marginTop = marginTop;
            table.marginBottom = marginBottom;
            table.marginLeft = marginLeft;
            table.marginRight = marginRight;
            table.paddingTop = paddingTop;
            table.paddingBottom = paddingBottom;
            table.paddingLeft = paddingLeft;
            table.paddingRight = paddingRight;
            table.gravity = gravity;
            table.textStyle = textStyle;
            table.createTime = createTime;
            table.updateTime = updateTime;
            return table;
        }
    }

    private String text;
    private int textSize;
    private int textColor;
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
    private long createTime;
    private long updateTime;

    private TextViewTable() { }

    public String getText() {
        return text;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getFgColor() {
        return fgColor;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public int getGravity() {
        return gravity;
    }

    public int getTextStyle() {
        return textStyle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public Builder builder() {
        Builder builder = new Builder();
        builder.text = text;
        builder.textSize = textSize;
        builder.textColor = textColor;
        builder.width = width;
        builder.height = height;
        builder.bgColor = bgColor;
        builder.fgColor = fgColor;
        builder.marginTop = marginTop;
        builder.marginBottom = marginBottom;
        builder.marginLeft = marginLeft;
        builder.marginRight = marginRight;
        builder.paddingTop = paddingTop;
        builder.paddingBottom = paddingBottom;
        builder.paddingLeft = paddingLeft;
        builder.paddingRight = paddingRight;
        builder.gravity = gravity;
        builder.textStyle = textStyle;
        builder.createTime = createTime;
        builder.updateTime = updateTime;
        return builder;
    }

    static void create(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + " (" +
                "id integer primary key autoincrement, " +
                TEXT + " text, " +
                TEXT_SIZE + " integer, " +
                TEXT_COLOR + " integer, " +
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
                CREATE_TIME + " integer, " +
                UPDATE_TIME + " integer" +
                ")");
    }
}
