package com.wuruoye.know.model.beans

import android.os.Parcel
import android.os.Parcelable
import com.wuruoye.know.util.sql.table.RecordTypeTable

/**
 * Created at 2019/3/18 12:31 by wuruoye
 * Description:
 */
class RecordType(var id: Int,
                 var title: String,
                 var views: ArrayList<RecordView>,
                 var createTime: Long,
                 var updateTime: Long
) : Parcelable {
    constructor(table: RecordTypeTable, views: ArrayList<RecordView>): this(table.id,
            table.title, views, table.createTime, table.updateTime)

    constructor(title: String): this(-1, title, arrayListOf(), -1, -1)

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            ArrayList<RecordView>().apply { source.readList(this, RecordView::class.java.classLoader) },
            source.readLong(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeList(views)
        writeLong(createTime)
        writeLong(updateTime)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecordType> = object : Parcelable.Creator<RecordType> {
            override fun createFromParcel(source: Parcel): RecordType = RecordType(source)
            override fun newArray(size: Int): Array<RecordType?> = arrayOfNulls(size)
        }
    }
}
