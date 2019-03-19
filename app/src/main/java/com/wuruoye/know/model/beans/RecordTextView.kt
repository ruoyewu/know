package com.wuruoye.know.model.beans

import android.graphics.Color
import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.text.InputType
import android.view.Gravity

/**
 * Created at 2019/3/18 13:12 by wuruoye
 * Description:
 */
data class RecordTextView(
        var id: Int = -1,
        var text: String = "",
        var textSize: Int = 15,
        var textColor: Int = Color.BLACK,
        var hint: String = "",
        var hintSize: Int = 0,
        var hintColor: Int = Color.GRAY,
        var width: Int = -1,
        var height: Int = -2,
        var bgColor: Int = 0,
        var fgColor: Int = 0,
        var marginTop: Int = 0,
        var marginBottom: Int = 0,
        var marginLeft: Int = 0,
        var marginRight: Int = 0,
        var paddingTop: Int = 0,
        var paddingBottom: Int = 0,
        var paddingLeft: Int = 0,
        var paddingRight: Int = 0,
        var gravity: Int = Gravity.START and Gravity.TOP,
        var textStyle: Int = Typeface.NORMAL,
        var inputType: Int = InputType.TYPE_CLASS_TEXT,
        var minLine: Int = 1,
        var maxLine: Int = 1,
        var isEditable: Boolean = false,
        var createTime: Long = 0,
        var updateTime: Long = 0
) : RecordView, Parcelable {
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
