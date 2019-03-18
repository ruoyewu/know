package com.wuruoye.know.model.beans;

/**
 * Created at 2019/3/18 08:33 by wuruoye
 * Description:
 */
public class RecordTypeItem {
    public static final int TYPE_TEXT = 1;

    private int type;
    private String title;

    public RecordTypeItem(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
