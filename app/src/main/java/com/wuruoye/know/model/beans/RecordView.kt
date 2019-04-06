package com.wuruoye.know.model.beans

import android.os.Parcel
import android.os.Parcelable

/**
 * Created at 2019/3/18 13:11 by wuruoye
 * Description: shouldn't use its instance
 * the parent of RecordTextView
 */
open class RecordView(
        var id: Int = -1,
        var width: Int = -1,
        var height: Int = -2,
        var marginTop: Int = 10,
        var marginBottom: Int = 10,
        var marginLeft: Int = 0,
        var marginRight: Int = 0,
        var paddingTop: Int = 10,
        var paddingBottom: Int = 10,
        var paddingLeft: Int = 16,
        var paddingRight: Int = 16,
        var createTime: Long = -1,
        var updateTime: Long = -1
) : Parcelable {
    constructor(source: Parcel) : this(
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
            source.readLong(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(width)
        writeInt(height)
        writeInt(marginTop)
        writeInt(marginBottom)
        writeInt(marginLeft)
        writeInt(marginRight)
        writeInt(paddingTop)
        writeInt(paddingBottom)
        writeInt(paddingLeft)
        writeInt(paddingRight)
        writeLong(createTime)
        writeLong(updateTime)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecordView> = object : Parcelable.Creator<RecordView> {
            override fun createFromParcel(source: Parcel): RecordView = RecordView(source)
            override fun newArray(size: Int): Array<RecordView?> = arrayOfNulls(size)
        }
    }
}
