package com.wuruoye.know.model.beans

import android.os.Parcel
import android.os.Parcelable

/**
 * Created at 2019/3/18 13:11 by wuruoye
 * Description:
 */
open class RecordView(
        var id: Int = -1,
        var createTime: Long = -1,
        var updateTime: Long = -1
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readLong(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
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
