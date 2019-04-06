package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.RecordLayoutView

/**
 * Created at 2019/4/5 19:56 by wuruoye
 * Description:
 */
class LayoutViewTable(
        id: Int,
        val width: Int,
        val height: Int,
        val orientation: Int,
        val items: String,
        val bgColor: Int,
        val marginLeft: Int,
        val marginRight: Int,
        val marginTop: Int,
        val marginBottom: Int,
        val paddingLeft: Int,
        val paddingRight: Int,
        val paddingTop: Int,
        val paddingBottom: Int,
        val gravity: Int,
        val createTime: Long,
        val updateTime: Long
) : ViewTable(id){
    constructor(view: RecordLayoutView, items: String): this(view.id, view.width, view.height,
            view.orientation, items, view.bgColor, view.marginLeft, view.marginRight, view.marginTop,
            view.marginBottom, view.paddingLeft, view.paddingRight, view.paddingTop, view.paddingBottom,
            view.gravity, view.createTime, view.updateTime)

    override fun save(db: SQLiteDatabase): Int {
        return if (id < 0) {
            db.insert(NAME, null, contentValues()).toInt()
        } else {
            db.update(NAME, contentValues(), "id=?", arrayOf(id.toString()))
            id
        }
    }

    override fun delete(db: SQLiteDatabase): Int {
        return db.delete(NAME, "id=?", arrayOf(id.toString()))
    }

    override fun contentValues(): ContentValues {
        val values = ContentValues()
        values.put(WIDTH, width)
        values.put(HEIGHT, height)
        values.put(ORIENTATION, orientation)
        values.put(ITEMS, items)
        values.put(BG_COLOR, bgColor)
        values.put(MARGIN_LEFT, marginLeft)
        values.put(MARGIN_RIGHT, marginRight)
        values.put(MARGIN_TOP, marginTop)
        values.put(MARGIN_BOTTOM, marginBottom)
        values.put(PADDING_LEFT, paddingLeft)
        values.put(PADDING_RIGHT, paddingRight)
        values.put(PADDING_TOP, paddingTop)
        values.put(PADDING_BOTTOM, paddingBottom)
        values.put(GRAVITY, gravity)
        values.put(CREATE_TIME, createTime)
        values.put(UPDATE_TIME, updateTime)
        return values
    }

    companion object {
        const val NAME = "layout_view"
        const val WIDTH = "width"
        const val HEIGHT = "height"
        const val ORIENTATION = "orientation"
        const val ITEMS = "items"
        const val BG_COLOR = "bg_color"
        const val MARGIN_LEFT = "margin_left"
        const val MARGIN_RIGHT = "margin_right"
        const val MARGIN_TOP = "margin_top"
        const val MARGIN_BOTTOM = "margin_bottom"
        const val PADDING_LEFT = "padding_left"
        const val PADDING_RIGHT = "padding_right"
        const val PADDING_TOP = "padding_top"
        const val PADDING_BOTTOM = "padding_bottom"
        const val GRAVITY = "gravity"
        const val CREATE_TIME = "create_time"
        const val UPDATE_TIME = "update_time"

        fun create(db: SQLiteDatabase) {
            db.execSQL("create table " + NAME + " (" +
                    "id integer primary key autoincrement, " +
                    WIDTH + " integer, " +
                    HEIGHT + " integer, " +
                    ORIENTATION + " integer, " +
                    ITEMS + " text, " +
                    BG_COLOR + " integer, " +
                    MARGIN_TOP + " integer, " +
                    MARGIN_BOTTOM + " integer, " +
                    MARGIN_LEFT + " integer, " +
                    MARGIN_RIGHT + " integer, " +
                    PADDING_TOP + " integer, " +
                    PADDING_BOTTOM + " integer, " +
                    PADDING_LEFT + " integer, " +
                    PADDING_RIGHT + " integer, " +
                    GRAVITY + " integer, " +
                    CREATE_TIME + " integer, " +
                    UPDATE_TIME + " integer)")
        }

        fun query(db: SQLiteDatabase, id: Int): LayoutViewTable {
            val cursor = db.query(NAME, null, "id=?", arrayOf(id.toString()),
                    null, null, null)
            cursor.use {
                it.moveToFirst()
                return fromCursor(it)
            }
        }

        fun delete(db: SQLiteDatabase, id: Int) {
            db.delete(NAME, "id=?", arrayOf(id.toString()))
        }

        private fun fromCursor(cursor: Cursor): LayoutViewTable {
            val id = cursor.getInt(0)
            val width = cursor.getInt(1)
            val height = cursor.getInt(2)
            val orientation = cursor.getInt(3)
            val items = cursor.getString(4)
            val bgColor = cursor.getInt(5)
            val marginTop = cursor.getInt(6)
            val marginBottom = cursor.getInt(7)
            val marginLeft = cursor.getInt(8)
            val marginRight = cursor.getInt(9)
            val paddingTop = cursor.getInt(10)
            val paddingBottom = cursor.getInt(11)
            val paddingLeft = cursor.getInt(12)
            val paddingRight = cursor.getInt(13)
            val gravity = cursor.getInt(14)
            val createTime = cursor.getLong(15)
            val updateTime = cursor.getLong(16)
            return LayoutViewTable(id, width, height, orientation, items, bgColor,
                    marginLeft, marginRight, marginTop, marginBottom, paddingLeft,
                    paddingRight, paddingTop, paddingBottom, gravity, createTime, updateTime)
        }
    }
}