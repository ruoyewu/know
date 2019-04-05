package com.wuruoye.know.model.beans

import android.os.Parcel
import android.os.Parcelable

/**
 * Created at 2019/4/1 10:34 by wuruoye
 * Description:
 */
class Record(
        var id: Int,
        var type: Int,
        var items: ArrayList<String>,
        var reviewNum: Int,
        var failNum: Int,
        var lastReview: Long,
        var createTime: Long,
        var updateTime: Long
) : Parcelable {
    constructor(type: Int) : this(-1, type, arrayListOf(), 0, 0,
            -1, -1, -1)

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.createStringArrayList(),
            source.readInt(),
            source.readInt(),
            source.readLong(),
            source.readLong(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(type)
        writeStringList(items)
        writeInt(reviewNum)
        writeInt(failNum)
        writeLong(lastReview)
        writeLong(createTime)
        writeLong(updateTime)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Record> = object : Parcelable.Creator<Record> {
            override fun createFromParcel(source: Parcel): Record = Record(source)
            override fun newArray(size: Int): Array<Record?> = arrayOfNulls(size)
        }
    }
}