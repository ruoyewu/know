package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.RecordImageView

/**
 * Created at 2019/4/6 20:14 by wuruoye
 * Description:
 */
class ImageViewTable(
        id: Int,
        val width: Int,
        val height: Int,
        val scaleType: Int,
        val shape: Int,
        val marginLeft: Int,
        val marginRight: Int,
        val marginTop: Int,
        val marginBottom: Int,
        val paddingLeft: Int,
        val paddingRight: Int,
        val paddingTop: Int,
        val paddingBottom: Int,
        val createTime: Long,
        val updateTime: Long
) : ViewTable(id){
    constructor(view: RecordImageView): this(view.id, view.width, view.height, view.scaleType,
            view.shape, view.marginLeft, view.marginRight, view.marginTop, view.marginBottom,
            view.paddingLeft, view.paddingRight, view.paddingTop, view.paddingBottom,
            view.createTime, view.updateTime)

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
        values.put(SCALE_TYPE, scaleType)
        values.put(SHAPE, shape)
        values.put(MARGIN_LEFT, marginLeft)
        values.put(MARGIN_RIGHT, marginRight)
        values.put(MARGIN_TOP, marginTop)
        values.put(MARGIN_BOTTOM, marginBottom)
        values.put(PADDING_LEFT, paddingLeft)
        values.put(PADDING_RIGHT, paddingRight)
        values.put(PADDING_TOP, paddingTop)
        values.put(PADDING_BOTTOM, paddingBottom)
        values.put(CREATE_TIME, createTime)
        values.put(UPDATE_TIME, updateTime)
        return values
    }

    companion object {
        val NAME = "image_view"
        val WIDTH = "width"
        val HEIGHT = "height"
        val SCALE_TYPE = "scale_type"
        val SHAPE = "shape"
        const val MARGIN_LEFT = "margin_left"
        const val MARGIN_RIGHT = "margin_right"
        const val MARGIN_TOP = "margin_top"
        const val MARGIN_BOTTOM = "margin_bottom"
        const val PADDING_LEFT = "padding_left"
        const val PADDING_RIGHT = "padding_right"
        const val PADDING_TOP = "padding_top"
        const val PADDING_BOTTOM = "padding_bottom"
        const val CREATE_TIME = "create_time"
        const val UPDATE_TIME = "update_time"

        fun create(db: SQLiteDatabase) {
            db.execSQL("create table $NAME (" +
                    "id integer primary key autoincrement, " +
                    "$WIDTH integer, " +
                    "$HEIGHT integer, " +
                    "$SCALE_TYPE integer, " +
                    "$SHAPE integer, " +
                    "$MARGIN_TOP integer, " +
                    "$MARGIN_BOTTOM integer, " +
                    "$MARGIN_LEFT integer, " +
                    "$MARGIN_RIGHT integer, " +
                    "$PADDING_TOP integer, " +
                    "$PADDING_BOTTOM integer, " +
                    "$PADDING_LEFT integer, " +
                    "$PADDING_RIGHT integer, " +
                    "$CREATE_TIME integer, " +
                    "$UPDATE_TIME integer)")
        }

        fun query(db: SQLiteDatabase, id: Int): RecordImageView? {
            val cursor = db.query(NAME, null, "id=?", arrayOf(id.toString()),
                    null, null, null)
            cursor.use {
                it.moveToFirst()
                if (!it.isAfterLast) {
                    return fromCursor(cursor)
                }
                return null
            }
        }

        fun delete(db: SQLiteDatabase, id: Int) {
            db.delete(NAME, "id=?", arrayOf(id.toString()))
        }

        private fun fromCursor(cursor: Cursor): RecordImageView {
            val id = cursor.getInt(0)
            val width = cursor.getInt(1)
            val height = cursor.getInt(2)
            val scaleType = cursor.getInt(3)
            val shape = cursor.getInt(4)
            val marginTop = cursor.getInt(5)
            val marginBottom = cursor.getInt(6)
            val marginLeft = cursor.getInt(7)
            val marginRight = cursor.getInt(8)
            val paddingTop = cursor.getInt(9)
            val paddingBottom = cursor.getInt(10)
            val paddingLeft = cursor.getInt(11)
            val paddingRight = cursor.getInt(12)
            val createTime = cursor.getLong(13)
            val updateTime = cursor.getLong(14)
            return RecordImageView(id, width, height, scaleType, shape, marginTop, marginBottom,
                    marginLeft, marginRight, paddingTop, paddingBottom, paddingLeft,
                    paddingRight, createTime, updateTime)
        }
    }
}