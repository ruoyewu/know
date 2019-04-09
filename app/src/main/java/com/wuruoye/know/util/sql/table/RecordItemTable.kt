package com.wuruoye.know.util.sql.table

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.wuruoye.know.model.beans.RecordItem

/**
 * Created at 2019/4/6 15:31 by wuruoye
 * Description:
 */
class RecordItemTable(
        id: Int,
        val recordId: Int,
        val type: Int,
        val typeId: Int,
        var content: String
) : Table(id) {
    constructor(item: RecordItem): this(item.id, item.recordId, item.type, item.typeId, item.content)

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
        values.put(RECORD_ID, recordId)
        values.put(TYPE, type)
        values.put(TYPE_ID, typeId)
        values.put(CONTENT, content)
        return values
    }

    companion object {
        const val NAME = "record_item"
        const val RECORD_ID = "record_id"
        const val TYPE = "type"
        const val TYPE_ID = "type_id"
        const val CONTENT = "content"

        fun create(db: SQLiteDatabase) {
            db.execSQL("create table " + NAME + " (" +
                    "id integer primary key autoincrement, " +
                    RECORD_ID + " integer, " +
                    TYPE + " integer, " +
                    TYPE_ID + " integer, " +
                    CONTENT + " text)")
        }

        fun query(db: SQLiteDatabase, recordId: Int, type: Int, typeId: Int): RecordItem? {
            val cursor = db.query(NAME, null, "$RECORD_ID=? and $TYPE=? and $TYPE_ID=?",
                    arrayOf(recordId.toString(), type.toString(), typeId.toString()), null,
                    null, null)
            cursor.use {
                it.moveToFirst()
                if (!it.isAfterLast) {
                    return fromCursor(cursor)
                }
                return null
            }
        }

        fun query(db: SQLiteDatabase, recordId: Int, type: Int): RecordItem? {
            val cursor = db.query(NAME, null, "$RECORD_ID=? and $TYPE=?",
                    arrayOf(recordId.toString(), type.toString()),
                    null, null, "id")
            cursor.use {
                it.moveToFirst()
                if (!it.isAfterLast) {
                    return fromCursor(cursor)
                }
                return null
            }
        }

        fun queryByRecord(db: SQLiteDatabase, recordId: Int): ArrayList<RecordItem> {
            val cursor = db.query(NAME, null, "$RECORD_ID=?",
                    arrayOf(recordId.toString()), null, null, null)
            val items = ArrayList<RecordItem>()
            cursor.use {
                it.moveToFirst()
                while (!it.isAfterLast) {
                    items.add(fromCursor(it))
                    it.moveToNext()
                }
            }
            return items
        }

        fun delete(db: SQLiteDatabase, recordId: Int) {
            db.delete(NAME, "$RECORD_ID=?", arrayOf(recordId.toString()))
        }

        fun delete(db: SQLiteDatabase, type: Int, typeId: Int) {
            db.delete(NAME, "$TYPE_ID=? and $TYPE=?",
                    arrayOf(typeId.toString(), type.toString()))
        }

        private fun fromCursor(cursor: Cursor): RecordItem {
            val id = cursor.getInt(0)
            val recordId = cursor.getInt(1)
            val type = cursor.getInt(2)
            val typeId = cursor.getInt(3)
            val content = cursor.getString(4)
            return RecordItem(id, recordId, type, typeId, content)
        }
    }
}