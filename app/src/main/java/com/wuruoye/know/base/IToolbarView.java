package com.wuruoye.know.base;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;

public interface IToolbarView {
    public interface ToolbarClickListener {
        void onBackClick();
    }

    Toolbar getToolbar();
    void setToolbarClickListener(ToolbarClickListener listener);
    void setToolbarBack(@DrawableRes int resource, String title);
    void setToolbarTitle(String title);
}
