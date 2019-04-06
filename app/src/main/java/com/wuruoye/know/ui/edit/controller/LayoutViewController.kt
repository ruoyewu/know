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
import com.wuruoye.know.model.beans.RecordLayoutView
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.util.ColorUtil

/**
 * Created at 2019/4/5 22:09 by wuruoye
 * Description:
 */
class LayoutViewController(private val mView: RecordLayoutView) :
        AbstractEditorController(), View.OnClickListener {
    private lateinit var flContent: FrameLayout
    private lateinit var svOptions: ScrollView

    private lateinit var mShowView: LinearLayout
    private lateinit var llWidth: LinearLayout
    private lateinit var tvWidth: TextView
    private lateinit var llHeight: LinearLayout
    private lateinit var tvHeight: TextView
    private lateinit var llOrientation: LinearLayout
    private lateinit var tvOrientation: TextView
    private lateinit var llBgColor: LinearLayout
    private lateinit var tvBgColor: TextView
    private lateinit var llMargin: LinearLayout
    private lateinit var tvMargin: TextView
    private lateinit var llPadding: LinearLayout
    private lateinit var tvPadding: TextView
    private lateinit var llGravity: LinearLayout
    private lateinit var tvGravity: TextView

    override fun attach(context: Context, fl: FrameLayout, sv: ScrollView) {
        super.attach(context)
        flContent = fl
        svOptions = sv

        initView()
    }

    private fun initView() {
        mShowView = ViewFactory.generateView(mContext, mView, flContent) as LinearLayout
        LayoutInflater.from(mContext)
                .inflate(R.layout.layout_layout_view, svOptions)

        with(svOptions) {
            llWidth = findViewById(R.id.ll_width_layout_layout)
            llHeight = findViewById(R.id.ll_height_layout_layout)
            llOrientation = findViewById(R.id.ll_orientation_layout_layout)
            llBgColor = findViewById(R.id.ll_bg_color_layout_layout)
            llMargin = findViewById(R.id.ll_margin_layout_layout)
            llPadding = findViewById(R.id.ll_padding_layout_layout)
            llGravity = findViewById(R.id.ll_gravity_layout_layout)

            tvWidth = findViewById(R.id.tv_width_layout_layout)
            tvHeight = findViewById(R.id.tv_height_layout_layout)
            tvOrientation = findViewById(R.id.tv_orientation_layout_layout)
            tvBgColor = findViewById(R.id.tv_bg_color_layout_layout)
            tvMargin = findViewById(R.id.tv_margin_layout_layout)
            tvPadding = findViewById(R.id.tv_padding_layout_layout)
            tvGravity = findViewById(R.id.tv_gravity_layout_layout)
        }

        llWidth.setOnClickListener(this)
        llHeight.setOnClickListener(this)
        llOrientation.setOnClickListener(this)
        llBgColor.setOnClickListener(this)
        llMargin.setOnClickListener(this)
        llPadding.setOnClickListener(this)
        llGravity.setOnClickListener(this)

        with(mView) {
            tvWidth.text = length2String(width)
            tvHeight.text = length2String(height)
            tvOrientation.text = ORIENTATION_NAME[ORIENTATION_VALUE.indexOf(orientation)]
            tvBgColor.text = ColorUtil.color2hex(bgColor)
            tvMargin.text = margin2String(marginLeft, marginTop, marginRight, marginBottom)
            tvPadding.text = margin2String(paddingLeft, paddingTop, paddingRight, paddingBottom)
            tvGravity.text = GRAVITY_NAME[if (GRAVITY_VALUE.indexOf(gravity) < 0) 0
                                            else GRAVITY_VALUE.indexOf(gravity)]
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ll_width_layout_layout -> {
                mCurType = TYPE_WIDTH
                showLengthDlg(mView.width)
            }
            R.id.ll_height_layout_layout -> {
                mCurType = TYPE_HEIGHT
                showLengthDlg(mView.height)
            }
            R.id.ll_orientation_layout_layout -> {
                mCurType = TYPE_ORIENTATION
                showSelectDlg(ORIENTATION_NAME, ORIENTATION_VALUE.indexOf(mView.orientation))
            }
            R.id.ll_bg_color_layout_layout -> {
                mCurType = TYPE_BG_COLOR
                showColorDlg(mView.bgColor)
            }
            R.id.ll_margin_layout_layout -> {
                mCurType = TYPE_MARGIN
                with(mView) {
                    showMarginDlg(marginLeft, marginTop, marginRight, marginBottom)
                }
            }
            R.id.ll_padding_layout_layout -> {
                mCurType = TYPE_PADDING
                with(mView) {
                    showMarginDlg(paddingLeft, paddingTop, paddingRight, paddingBottom)
                }
            }
            R.id.ll_gravity_layout_layout -> {
                mCurType = TYPE_GRAVITY
                showSelectDlg(GRAVITY_NAME, GRAVITY_VALUE.indexOf(mView.gravity))
            }
        }
    }

    override fun onItemSelect(value: Int) {
        super.onItemSelect(value)
        when (mCurType) {
            TYPE_ORIENTATION -> {
                mView.orientation = ORIENTATION_VALUE[value]
                tvOrientation.text = ORIENTATION_NAME[value]

                mShowView.orientation = ORIENTATION_VALUE[value]
            }
            TYPE_GRAVITY -> {
                mView.gravity = GRAVITY_VALUE[value]
                tvGravity.text = GRAVITY_NAME[value]

                mShowView.gravity = GRAVITY_VALUE[value]
            }
        }
    }

    override fun onMarginSubmit(left: Int, top: Int, right: Int, bottom: Int) {
        when (mCurType) {
            TYPE_MARGIN -> {
                mView.marginLeft = left
                mView.marginTop = top
                mView.marginRight = right
                mView.marginBottom = bottom
                tvMargin.text = margin2String(left, top, right, bottom)

                val lp = mShowView.layoutParams as ViewGroup.MarginLayoutParams
                lp.setMargins(toPx(left), toPx(top), toPx(right), toPx(bottom))
                mShowView.layoutParams = lp
            }
            TYPE_PADDING -> {
                mView.paddingLeft = left
                mView.paddingTop = top
                mView.paddingRight = right
                mView.paddingBottom = bottom
                tvPadding.text = margin2String(left, top, right, bottom)

                mShowView.setPadding(toPx(left), toPx(top), toPx(right), toPx(bottom))
            }
        }
    }

    override fun onColorSubmit(color: Int) {
        when (mCurType) {
            TYPE_BG_COLOR -> {
                mView.bgColor = color
                tvBgColor.text = ColorUtil.color2hex(color)

                mShowView.setBackgroundColor(color)
            }
        }
    }

    override fun onLengthSubmit(length: Int) {
        when (mCurType) {
            TYPE_WIDTH -> {
                mView.width = length
                tvWidth.text = length2String(length)

                val lp = mShowView.layoutParams
                lp.width = lengthToPx(length)
                mShowView.layoutParams = lp
            }
            TYPE_HEIGHT -> {
                mView.height = length
                tvHeight.text = length2String(length)

                val lp = mShowView.layoutParams
                lp.height = lengthToPx(length)
                mShowView.layoutParams = lp
            }
        }
    }

    override val result: RecordView
        get() = mView
}