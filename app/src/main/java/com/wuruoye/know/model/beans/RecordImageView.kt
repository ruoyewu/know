package com.wuruoye.know.model.beans

import android.os.Parcel
import android.os.Parcelable

/**
 * Created at 2019/4/6 20:24 by wuruoye
 * Description:
 */
class RecordImageView(
        id: Int = -1,
        width: Int = -1,
        height: Int = 100,
        var scaleType: Int = 0,
        var shape: Int = 0,
        marginTop: Int = 0,
        marginBottom: Int = 0,
        marginLeft: Int = 0,
        marginRight: Int = 0,
        paddingTop: Int = 0,
        paddingBottom: Int = 0,
        paddingLeft: Int = 0,
        paddingRight: Int = 0,
        createTime: Long = -1,
        updateTime: Long = -1
) : RecordView(id, width, height, marginTop, marginBottom, marginLeft, marginRight, paddingTop,
        paddingBottom, paddingLeft, paddingRight, createTime, updateTime), Parcelable {
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
        writeInt(scaleType)
        writeInt(shape)
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
        val CREATOR: Parcelable.Creator<RecordImageView> = object : Parcelable.Creator<RecordImageView> {
            override fun createFromParcel(source: Parcel): RecordImageView = RecordImageView(source)
            override fun newArray(size: Int): Array<RecordImageView?> = arrayOfNulls(size)
        }
    }
}