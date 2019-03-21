package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.RecordTextView

class TextViewTable(id: Int,
                    private val text: String,
                    private val textSize: Int,
                    private val textColor: Int,
                    private val hint: String,
                    private val hintSize: Int,
                    private val hintColor: Int,
                    private val width: Int,
                    private val height: Int,
                    private val bgColor: Int,
                    private val fgColor: Int,
                    private val marginTop: Int,
                    private val marginBottom: Int,
                    private val marginLeft: Int,
                    private val marginRight: Int,
                    private val paddingTop: Int,
                    private val paddingBottom: Int,
                    private val paddingLeft: Int,
                    private val paddingRight: Int,
                    private val gravity: Int,
                    private val textStyle: Int,
                    private val inputType: Int,
                    private val minLine: Int,
                    private val maxLine: Int,
                    private val editable: Boolean,
                    private val createTime: Long,
                    private val updateTime: Long) : ViewTable(id) {

    constructor(view: RecordTextView) : this(view.id, view.text, view.textSize, view.textColor,
            view.hint, view.hintSize, view.hintColor, view.width, view.height, view.bgColor,
            view.fgColor, view.marginTop, view.marginBottom, view.marginLeft, view.marginRight,
            view.paddingTop, view.paddingBottom, view.paddingLeft, view.paddingRight, view.gravity,
            view.textStyle, view.inputType, view.minLine, view.maxLine, view.isEditable,
            view.createTime, view.updateTime)

    override fun save(db: SQLiteDatabase): Int {
        id = db.insert(NAME, null, contentValues()).toInt()
        return id
    }

    override fun delete(db: SQLiteDatabase): Int {
        return db.delete(NAME, "id=?", arrayOf(id.toString()))
    }

    override fun update(db: SQLiteDatabase): Int {
        return db.update(NAME, contentValues(), "id=?", arrayOf(id.toString()))
    }

    override fun contentValues(): ContentValues {
        val values = ContentValues()
        values.put(TEXT, text)
        values.put(TEXT_SIZE, textSize)
        values.put(TEXT_COLOR, textColor)
        values.put(HINT, hint)
        values.put(HINT_SIZE, hintSize)
        values.put(HINT_COLOR, hintColor)
        values.put(WIDTH, width)
        values.put(HEIGHT, height)
        values.put(BG_COLOR, bgColor)
        values.put(FG_COLOR, fgColor)
        values.put(MARGIN_LEFT, marginLeft)
        values.put(MARGIN_RIGHT, marginRight)
        values.put(MARGIN_TOP, marginTop)
        values.put(MARGIN_BOTTOM, marginBottom)
        values.put(PADDING_LEFT, paddingLeft)
        values.put(PADDING_RIGHT, paddingRight)
        values.put(PADDING_TOP, paddingTop)
        values.put(PADDING_BOTTOM, paddingBottom)
        values.put(GRAVITY, gravity)
        values.put(TEXT_STYLE, textStyle)
        values.put(INPUT_TYPE, inputType)
        values.put(MIN_LINE, minLine)
        values.put(MAX_LINE, maxLine)
        values.put(EDITABLE, editable)
        values.put(CREATE_TIME, createTime)
        values.put(UPDATE_TIME, updateTime)
        return values
    }

    companion object {
        val NAME = "text_view"
        val TEXT = "text"
        val TEXT_SIZE = "text_size"
        val TEXT_COLOR = "text_color"
        val HINT = "hint"
        val HINT_SIZE = "hint_size"
        val HINT_COLOR = "hint_color"
        val WIDTH = "width"
        val HEIGHT = "height"
        val BG_COLOR = "bg_color"
        val FG_COLOR = "fg_color"
        val MARGIN_LEFT = "margin_left"
        val MARGIN_RIGHT = "margin_right"
        val MARGIN_TOP = "margin_top"
        val MARGIN_BOTTOM = "margin_bottom"
        val PADDING_LEFT = "padding_left"
        val PADDING_RIGHT = "padding_right"
        val PADDING_TOP = "padding_top"
        val PADDING_BOTTOM = "padding_bottom"
        val GRAVITY = "gravity"
        val TEXT_STYLE = "text_style"
        val INPUT_TYPE = "input_type"
        val MIN_LINE = "min_line"
        val MAX_LINE = "max_line"
        val EDITABLE = "editable"
        val CREATE_TIME = "create_time"
        val UPDATE_TIME = "update_time"

        fun create(db: SQLiteDatabase) {
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
                    MIN_LINE + " integer, " +
                    MAX_LINE + " integer, " +
                    EDITABLE + " integer ," +
                    CREATE_TIME + " integer, " +
                    UPDATE_TIME + " integer" +
                    ")")
        }
    }
}
