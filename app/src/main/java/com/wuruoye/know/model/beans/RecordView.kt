package com.wuruoye.know.model.beans

import android.os.Parcel
import android.os.Parcelable

/**
 * Created at 2019/3/18 13:11 by wuruoye
 * Description:
 */
open class RecordView() : Parcelable {


    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecordView> = object : Parcelable.Creator<RecordView> {
            override fun createFromParcel(source: Parcel): RecordView = RecordView(source)
            override fun newArray(size: Int): Array<RecordView?> = arrayOfNulls(size)
        }
    }
}
