package com.wuruoye.know.model.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created at 2019/3/18 13:12 by wuruoye
 * Description:
 */
public class RecordTextView implements RecordView, Parcelable {
    private int id;
    private String text;
    private int textSize;
    private int textColor;
    private String hint;
    private int hintSize;
    private int hintColor;
    private int width;
    private int height;
    private int bgColor;
    private int fgColor;
    private int marginTop;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;
    private int paddingTop;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int gravity;
    private int textStyle;
    private int inputType;
    private boolean editable;
    private long createTime;
    private long updateTime;

    public RecordTextView(int id, String text, int textSize, int textColor, String hint,
                          int hintSize, int hintColor, int width, int height, int bgColor,
                          int fgColor, int marginTop, int marginBottom, int marginLeft,
                          int marginRight, int paddingTop, int paddingBottom, int paddingLeft,
                          int paddingRight, int gravity, int textStyle, int inputType,
                          boolean editable, long createTime, long updateTime) {
        this.id = id;
        this.text = text;
        this.textSize = textSize;
        this.textColor = textColor;
        this.hint = hint;
        this.hintSize = hintSize;
        this.hintColor = hintColor;
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.paddingTop = paddingTop;
        this.paddingBottom = paddingBottom;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.gravity = gravity;
        this.textStyle = textStyle;
        this.inputType = inputType;
        this.editable = editable;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getHintSize() {
        return hintSize;
    }

    public void setHintSize(int hintSize) {
        this.hintSize = hintSize;
    }

    public int getHintColor() {
        return hintColor;
    }

    public void setHintColor(int hintColor) {
        this.hintColor = hintColor;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getFgColor() {
        return fgColor;
    }

    public void setFgColor(int fgColor) {
        this.fgColor = fgColor;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(int textStyle) {
        this.textStyle = textStyle;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
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
        dest.writeString(this.text);
        dest.writeInt(this.textSize);
        dest.writeInt(this.textColor);
        dest.writeString(this.hint);
        dest.writeInt(this.hintSize);
        dest.writeInt(this.hintColor);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.bgColor);
        dest.writeInt(this.fgColor);
        dest.writeInt(this.marginTop);
        dest.writeInt(this.marginBottom);
        dest.writeInt(this.marginLeft);
        dest.writeInt(this.marginRight);
        dest.writeInt(this.paddingTop);
        dest.writeInt(this.paddingBottom);
        dest.writeInt(this.paddingLeft);
        dest.writeInt(this.paddingRight);
        dest.writeInt(this.gravity);
        dest.writeInt(this.textStyle);
        dest.writeInt(this.inputType);
        dest.writeByte(this.editable ? (byte) 1 : (byte) 0);
        dest.writeLong(this.createTime);
        dest.writeLong(this.updateTime);
    }

    protected RecordTextView(Parcel in) {
        this.id = in.readInt();
        this.text = in.readString();
        this.textSize = in.readInt();
        this.textColor = in.readInt();
        this.hint = in.readString();
        this.hintSize = in.readInt();
        this.hintColor = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
        this.bgColor = in.readInt();
        this.fgColor = in.readInt();
        this.marginTop = in.readInt();
        this.marginBottom = in.readInt();
        this.marginLeft = in.readInt();
        this.marginRight = in.readInt();
        this.paddingTop = in.readInt();
        this.paddingBottom = in.readInt();
        this.paddingLeft = in.readInt();
        this.paddingRight = in.readInt();
        this.gravity = in.readInt();
        this.textStyle = in.readInt();
        this.inputType = in.readInt();
        this.editable = in.readByte() != 0;
        this.createTime = in.readLong();
        this.updateTime = in.readLong();
    }

    public static final Creator<RecordTextView> CREATOR = new Creator<RecordTextView>() {
        @Override
        public RecordTextView createFromParcel(Parcel source) {
            return new RecordTextView(source);
        }

        @Override
        public RecordTextView[] newArray(int size) {
            return new RecordTextView[size];
        }
    };
}
