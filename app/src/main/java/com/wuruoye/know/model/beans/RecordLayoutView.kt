package com.wuruoye.know.model.beans

import android.os.Parcel
import android.os.Parcelable
import android.view.Gravity
import android.widget.LinearLayout
import com.wuruoye.know.util.sql.table.LayoutViewTable

/**
 * Created at 2019/4/5 20:11 by wuruoye
 * Description:
 */
class RecordLayoutView(
        id: Int = -1,
        var width: Int = -1,
        var height: Int = -2,
        var bgColor: Int = 0,
        var orientation: Int = LinearLayout.VERTICAL,
        var views: ArrayList<RecordView>,
        var marginTop: Int = 0,
        var marginBottom: Int = 0,
        var marginLeft: Int = 0,
        var marginRight: Int = 0,
        var paddingTop: Int = 0,
        var paddingBottom: Int = 0,
        var paddingLeft: Int = 0,
        var paddingRight: Int = 0,
        var gravity: Int = Gravity.CENTER,
        createTime: Long = -1,
        updateTime: Long = -1
) : RecordView(id, createTime, updateTime), Parcelable {
    constructor(): this(-1, -1, -2, 0, LinearLayout.VERTICAL, ArrayList(),
            0, 0, 0, 0, 0, 0,
            0, 0, Gravity.CENTER, -1, -1)

    constructor(table: LayoutViewTable, views: ArrayList<RecordView>) :
            this(table.id, table.width, table.height, table.bgColor, table.orientation,
                    views, table.marginTop, table.marginBottom, table.marginLeft,
                    table.marginRight, table.paddingTop, table.paddingBottom, table.paddingLeft,
                    table.paddingRight, table.gravity, table.createTime, table.updateTime)

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.createTypedArrayList(RecordView.CREATOR),
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
        writeInt(bgColor)
        writeInt(orientation)
        writeTypedList(views)
        writeInt(marginTop)
        writeInt(marginBottom)
        writeInt(marginLeft)
        writeInt(marginRight)
        writeInt(paddingTop)
        writeInt(paddingBottom)
        writeInt(paddingLeft)
        writeInt(paddingRight)
        writeInt(gravity)
        writeLong(createTime)
        writeLong(updateTime)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecordLayoutView> = object : Parcelable.Creator<RecordLayoutView> {
            override fun createFromParcel(source: Parcel): RecordLayoutView = RecordLayoutView(source)
            override fun newArray(size: Int): Array<RecordLayoutView?> = arrayOfNulls(size)
        }
    }
}