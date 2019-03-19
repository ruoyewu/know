package com.wuruoye.know.base

import android.support.annotation.DrawableRes
import android.support.v7.widget.Toolbar

interface IToolbarView {

    interface OnToolbarBackListener {
        fun onBackClick()
    }

    interface OnToolbarMoreListener {
        fun onMoreClick()
    }

    interface OnToolbarTitleListener {
        fun onTitleClick()
    }

    fun setToolbarBackListener(listener: OnToolbarBackListener)
    fun setToolbarMoreListener(listener: OnToolbarMoreListener)
    fun setToolbarTitleListener(listener: OnToolbarTitleListener)
    fun setToolbarBack(@DrawableRes resource: Int, title: String)
    fun setToolbarMore(@DrawableRes resource: Int, title: String)
    fun setToolbarTitle(title: String)
    fun getToolbar(): Toolbar
}
