package com.wuruoye.know.ui.edit.controller;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.wuruoye.know.model.beans.RecordView;

/**
 * Created at 2019/3/18 16:42 by wuruoye
 * Description:
 */
public interface EditorController {
    void attach(Context context, FrameLayout fl, ScrollView sv);
    RecordView getResult();
}
