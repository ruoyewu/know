package com.wuruoye.know.ui.edit.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.wuruoye.know.R
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordView

/**
 * Created at 2019/3/18 16:52 by wuruoye
 * Description:
 */
class TextViewController(mView: RecordView) : AbstractEditorController(), View.OnClickListener {
    private val mView: RecordTextView = mView as RecordTextView
    private var flContent: FrameLayout? = null
    private var svOptions: ScrollView? = null

    private var mShowView: TextView? = null
    private var llText: LinearLayout? = null
    private var tvText: TextView? = null
    private var llTextSize: LinearLayout? = null
    private var tvTextSize: TextView? = null
    private var llTextColor: LinearLayout? = null
    private var tvTextColor: TextView? = null
    private var llMargin: LinearLayout? = null
    private var tvMargin: TextView? = null
    private var llPadding: LinearLayout? = null
    private var tvPadding: TextView? = null
    private var llTextStyle: LinearLayout? = null
    private var tvTextStyle: TextView? = null
    private var llBgColor: LinearLayout? = null
    private var tvBgColor: TextView? = null
    private var llFgColor: LinearLayout? = null
    private var tvFgColor: TextView? = null
    private var llMinLine: LinearLayout? = null
    private var tvMinLine: TextView? = null
    private var llMaxLine: LinearLayout? = null
    private val tvMaxLine: TextView? = null

    override fun attach(context: Context, fl: FrameLayout, sv: ScrollView) {
        super.attach(context)
        flContent = fl
        svOptions = sv

        initView()
    }

    private fun initView() {
        mShowView = ViewFactory.generateView(mContext, mView, flContent as ViewGroup) as TextView
        flContent!!.addView(mShowView)
        LayoutInflater.from(mContext)
                .inflate(R.layout.layout_text_view, svOptions)

        llText = flContent!!.findViewById(R.id.ll_text_layout_text)
        llTextSize = flContent!!.findViewById(R.id.ll_text_size_layout_text)
        llTextColor = flContent!!.findViewById(R.id.ll_text_color_layout_text)
        llBgColor = flContent!!.findViewById(R.id.ll_bg_color_layout_text)
        llFgColor = flContent!!.findViewById(R.id.ll_fg_color_layout_text)
        llMargin = flContent!!.findViewById(R.id.ll_margin_layout_text)
        llPadding = flContent!!.findViewById(R.id.ll_padding_layout_text)
        llTextStyle = flContent!!.findViewById(R.id.ll_text_style_layout_text)
        llMinLine = flContent!!.findViewById(R.id.ll_min_line_layout_text)
        llMaxLine = flContent!!.findViewById(R.id.ll_max_line_layout_text)
        tvText = flContent!!.findViewById(R.id.tv_text_layout_text)
        tvTextSize = flContent!!.findViewById(R.id.tv_text_size_layout_text)
        tvTextColor = flContent!!.findViewById(R.id.tv_text_color_layout_text)
        tvMargin = flContent!!.findViewById(R.id.tv_margin_layout_text)
        tvPadding = flContent!!.findViewById(R.id.tv_padding_layout_text)
        tvTextStyle = flContent!!.findViewById(R.id.tv_text_style_layout_text)
        tvBgColor = flContent!!.findViewById(R.id.tv_bg_color_layout_text)
        tvFgColor = flContent!!.findViewById(R.id.tv_fg_color_layout_text)
        tvMinLine = flContent!!.findViewById(R.id.tv_min_line_layout_text)
        tvMargin = flContent!!.findViewById(R.id.tv_margin_layout_text)
    }

    override val result: RecordView
        get() = mView

    override fun onMarginBtnClick(left: Int, top: Int, right: Int, bottom: Int) {

    }

    override fun onClick(v: View) {
        super.onClick(v)

    }
}
