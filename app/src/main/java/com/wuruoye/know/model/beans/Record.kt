package com.wuruoye.know.model.beans

import android.os.Parcel
import android.os.Parcelable

/**
 * Created at 2019/4/1 10:34 by wuruoye
 * Description:
 */
class Record(
        var id: Int = -1,
        var type: Int = -1,
        var reviewNum: Int = 0,
        var failNum: Int = 0,
        var lastReview: Long = -1,
        var createTime: Long = -1,
        var updateTime: Long = -1
) : Parcelable {
    constructor(type: Int) : this() {
        this.type = type
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
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