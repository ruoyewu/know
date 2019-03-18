package com.wuruoye.know.base;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;

public interface IToolbarView {
    interface OnToolbarBackListener {
        void onBackClick();
    }

    interface OnToolbarMoreListener {
        void onMoreClick();
    }

    Toolbar getToolbar();
    void setToolbarBackListener(OnToolbarBackListener listener);
    void setToolbarMoreListener(OnToolbarMoreListener listener);
    void setToolbarBack(@DrawableRes int resource, String title);
    void setToolbarMore(@DrawableRes int resource, String title);
    void setToolbarTitle(String title);
}
