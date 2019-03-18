package com.wuruoye.know.model.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at 2019/3/18 12:31 by wuruoye
 * Description:
 */
public class RecordType implements Parcelable {
    private int id;
    private String title;
    private List<RecordView> views;
    private long createTime;
    private long updateTime;

    public RecordType(int id, String title, List<RecordView> views,
                      long createTime, long updateTime) {
        this.id = id;
        this.title = title;
        this.views = views;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RecordView> getViews() {
        return views;
    }

    public void setViews(List<RecordView> views) {
        this.views = views;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeList(this.views);
        dest.writeLong(this.createTime);
        dest.writeLong(this.updateTime);
    }

    public RecordType() {
    }

    protected RecordType(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.views = new ArrayList<RecordView>();
        in.readList(this.views, RecordView.class.getClassLoader());
        this.createTime = in.readLong();
        this.updateTime = in.readLong();
    }

    public static final Creator<RecordType> CREATOR = new Creator<RecordType>() {
        @Override
        public RecordType createFromParcel(Parcel source) {
            return new RecordType(source);
        }

        @Override
        public RecordType[] newArray(int size) {
            return new RecordType[size];
        }
    };
}
