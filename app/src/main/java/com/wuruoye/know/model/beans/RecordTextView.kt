package com.wuruoye.know.model.beans

import android.graphics.Color
import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.text.InputType
import android.view.Gravity
import com.wuruoye.know.util.sql.table.TextViewTable

/**
 * Created at 2019/3/18 13:12 by wuruoye
 * Description:
 */
class RecordTextView(
        id: Int = -1,
        var text: String = "",
        var textSize: Int = 15,
        var textColor: Int = Color.BLACK,
        var hint: String = "",
        var hintSize: Int = 0,
        var hintColor: Int = Color.GRAY,
        width: Int = -1,
        height: Int = -2,
        var bgColor: Int = 0,
        var fgColor: Int = 0,
        marginTop: Int = 0,
        marginBottom: Int = 0,
        marginLeft: Int = 0,
        marginRight: Int = 0,
        paddingTop: Int = 0,
        paddingBottom: Int = 0,
        paddingLeft: Int = 0,
        paddingRight: Int = 0,
        var gravity: Int = Gravity.CENTER,
        var textStyle: Int = Typeface.NORMAL,
        var inputType: Int = InputType.TYPE_CLASS_TEXT,
        var minLine: Int = 1,
        var maxLine: Int = 1,
        var isEditable: Boolean = false,
        createTime: Long = -1,
        updateTime: Long = -1
) : RecordView(id, width, height, marginTop, marginBottom, marginLeft, marginRight, paddingTop,
        paddingBottom, paddingLeft, paddingRight, createTime, updateTime), Parcelable {
    constructor(table: TextViewTable): this(table.id, table.text, table.textSize, table.textColor,
            table.hint, table.hintSize, table.hintColor, table.width, table.height, table.bgColor,
            table.fgColor, table.marginTop, table.marginBottom, table.marginLeft, table.marginRight,
            table.paddingTop, table.paddingBottom, table.paddingLeft, table.paddingRight,
            table.gravity, table.textStyle, table.inputType, table.minLine, table.maxLine,
            table.editable, table.createTime, table.updateTime)

    constructor(editable: Boolean): this() {
        isEditable = editable
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            1 == source.readInt(),
            source.readLong(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(text)
        writeInt(textSize)
        writeInt(textColor)
        writeString(hint)
        writeInt(hintSize)
        writeInt(hintColor)
        writeInt(width)
        writeInt(height)
        writeInt(bgColor)
        writeInt(fgColor)
        writeInt(marginTop)
        writeInt(marginBottom)
        writeInt(marginLeft)
        writeInt(marginRight)
        writeInt(paddingTop)
        writeInt(paddingBottom)
        writeInt(paddingLeft)
        writeInt(paddingRight)
        writeInt(gravity)
        writeInt(textStyle)
        writeInt(inputType)
        writeInt(minLine)
        writeInt(maxLine)
        writeInt((if (isEditable) 1 else 0))
        writeLong(createTime)
        writeLong(updateTime)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecordTextView> = object : Parcelable.Creator<RecordTextView> {
            override fun createFromParcel(source: Parcel): RecordTextView = RecordTextView(source)
            override fun newArray(size: Int): Array<RecordTextView?> = arrayOfNulls(size)
        }
    }
}
