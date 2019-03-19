package com.wuruoye.know.ui.edit.controller

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
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
import com.wuruoye.know.util.ColorUtil
import com.wuruoye.library.util.DensityUtil

/**
 * Created at 2019/3/18 16:52 by wuruoye
 * Description:
 */
class TextViewController(mView: RecordView) : AbstractEditorController(), View.OnClickListener {
    private val mView: RecordTextView = mView as RecordTextView
    private lateinit var flContent: FrameLayout
    private lateinit var svOptions: ScrollView

    private lateinit var mShowView: TextView
    private lateinit var llText: LinearLayout
    private lateinit var tvText: TextView
    private lateinit var llTextSize: LinearLayout
    private lateinit var tvTextSize: TextView
    private lateinit var llTextColor: LinearLayout
    private lateinit var tvTextColor: TextView
    private lateinit var llMargin: LinearLayout
    private lateinit var tvMargin: TextView
    private lateinit var llPadding: LinearLayout
    private lateinit var tvPadding: TextView
    private lateinit var llTextStyle: LinearLayout
    private lateinit var tvTextStyle: TextView
    private lateinit var llBgColor: LinearLayout
    private lateinit var tvBgColor: TextView
    private lateinit var llFgColor: LinearLayout
    private lateinit var tvFgColor: TextView
    private lateinit var llGravity: LinearLayout
    private lateinit var tvGravity: TextView
    private lateinit var llMinLine: LinearLayout
    private lateinit var tvMinLine: TextView
    private lateinit var llMaxLine: LinearLayout
    private lateinit var tvMaxLine: TextView

    private var mCurType: Int = 0

    override fun attach(context: Context, fl: FrameLayout, sv: ScrollView) {
        super.attach(context)
        flContent = fl
        svOptions = sv

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        mShowView = ViewFactory.generateView(mContext, mView, flContent as ViewGroup) as TextView
        flContent.addView(mShowView)
        LayoutInflater.from(mContext)
                .inflate(R.layout.layout_text_view, svOptions)

        llText = svOptions.findViewById(R.id.ll_text_layout_text)
        llTextSize = svOptions.findViewById(R.id.ll_text_size_layout_text)
        llTextColor = svOptions.findViewById(R.id.ll_text_color_layout_text)
        llBgColor = svOptions.findViewById(R.id.ll_bg_color_layout_text)
        llFgColor = svOptions.findViewById(R.id.ll_fg_color_layout_text)
        llMargin = svOptions.findViewById(R.id.ll_margin_layout_text)
        llPadding = svOptions.findViewById(R.id.ll_padding_layout_text)
        llTextStyle = svOptions.findViewById(R.id.ll_text_style_layout_text)
        llGravity = svOptions.findViewById(R.id.ll_gravity_layout_text)
        llMinLine = svOptions.findViewById(R.id.ll_min_line_layout_text)
        llMaxLine = svOptions.findViewById(R.id.ll_max_line_layout_text)
        tvText = svOptions.findViewById(R.id.tv_text_layout_text)
        tvTextSize = svOptions.findViewById(R.id.tv_text_size_layout_text)
        tvTextColor = svOptions.findViewById(R.id.tv_text_color_layout_text)
        tvMargin = svOptions.findViewById(R.id.tv_margin_layout_text)
        tvPadding = svOptions.findViewById(R.id.tv_padding_layout_text)
        tvTextStyle = svOptions.findViewById(R.id.tv_text_style_layout_text)
        tvBgColor = svOptions.findViewById(R.id.tv_bg_color_layout_text)
        tvFgColor = svOptions.findViewById(R.id.tv_fg_color_layout_text)
        tvGravity = svOptions.findViewById(R.id.tv_gravity_layout_text)
        tvMinLine = svOptions.findViewById(R.id.tv_min_line_layout_text)
        tvMaxLine = svOptions.findViewById(R.id.tv_max_line_layout_text)

        llText.setOnClickListener(this)
        llTextSize.setOnClickListener(this)
        llTextColor.setOnClickListener(this)
        llBgColor.setOnClickListener(this)
        llFgColor.setOnClickListener(this)
        llMargin.setOnClickListener(this)
        llPadding.setOnClickListener(this)
        llTextStyle.setOnClickListener(this)
        llGravity.setOnClickListener(this)
        llMinLine.setOnClickListener(this)
        llMaxLine.setOnClickListener(this)

        with(mView) {
            tvText.text = text
            tvTextSize.text = textSize.toString()
            tvTextColor.text = ColorUtil.code2Hex(textColor)
            tvTextColor.setTextColor(textColor)
            tvBgColor.text = ColorUtil.code2Hex(bgColor)
            tvFgColor.text = ColorUtil.code2Hex(fgColor)
            tvMargin.text = "" + marginLeft + " | " + marginTop +
                    " | " + marginRight + " | " + marginBottom
            tvPadding.text = "" + paddingLeft + " | " + paddingTop +
                    " | " + paddingRight + " | " + paddingBottom
            tvTextStyle.text = TEXT_STYLE_NAME[TEXT_STYLE_VALUE.indexOf(textStyle)]
            tvGravity.text = GRAVITY_NAME[GRAVITY_VALUE.indexOf(gravity)]
            tvMinLine.text = minLine.toString()
            tvMaxLine.text = maxLine.toString()
        }
    }

    override val result: RecordView
        get() = mView

    @SuppressLint("SetTextI18n")
    override fun onMarginSubmit(left: Int, top: Int, right: Int, bottom: Int) {
        when (mCurType) {
            TYPE_MARGIN -> {
                with(mView) {
                    marginLeft = left
                    marginRight = right
                    marginTop = top
                    marginBottom = bottom
                }
                tvMargin.text = "" + left + " | " + top +
                        " | " + right + " | " + bottom
                val lp = mShowView.layoutParams as ViewGroup.MarginLayoutParams
                lp.setMargins(toPx(left), toPx(top), toPx(right), toPx(bottom))
                mShowView.layoutParams = lp
            }
            TYPE_PADDING -> {
                with(mView) {
                    paddingLeft = left
                    paddingRight = right
                    paddingTop = top
                    paddingBottom = bottom
                }
                tvPadding.text = "" + left + " | " + top +
                        " | " + right + " | " + bottom
                mShowView.setPadding(toPx(left), toPx(top), toPx(right), toPx(bottom))
            }
        }
    }

    override fun onEditSubmit(text: String) {
        when(mCurType) {
            TYPE_TEXT -> {
                mView.text = text
                mShowView.text = text
                tvText.text = text
            }
        }
    }

    override fun onItemSelect(value: Int) {
        when(mCurType) {
            TYPE_TEXT_SIZE -> {
                mView.textSize = value
                mShowView.textSize = value.toFloat()
                tvTextSize.text = value.toString()
            }
            TYPE_TEXT_STYLE -> {
                mView.textStyle = TEXT_STYLE_VALUE[value]
                mShowView.setTypeface(mShowView.typeface, TEXT_STYLE_VALUE[value])
                tvTextStyle.text = TEXT_STYLE_NAME[value]
                if (value == 0) {
                    mShowView.typeface = Typeface.DEFAULT
                }
            }
            TYPE_GRAVITY -> {
                mView.gravity = GRAVITY_VALUE[value]
                mShowView.gravity = GRAVITY_VALUE[value]
                tvGravity.text = GRAVITY_NAME[value]
            }
            TYPE_LINE_MIN -> {
                mView.minLine = value
                mShowView.minLines = value
                tvMinLine.text = value.toString()
            }
            TYPE_LINE_MAX -> {
                mView.maxLine = value
                mShowView.maxLines = value
                tvMaxLine.text = value.toString()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_text_layout_text -> {
                mCurType = TYPE_TEXT
                showEditDlg("输入文本内容", -1)
            }
            R.id.ll_text_size_layout_text -> {
                mCurType = TYPE_TEXT_SIZE
                showSelectDlg(TEXT_SIZE_MIN, TEXT_SIZE_MAX, mView.textSize)
            }
            R.id.ll_text_color_layout_text -> {
                mCurType = TYPE_TEXT_COLOR
            }
            R.id.ll_bg_color_layout_text -> {
                mCurType = TYPE_BG_COLOR
            }
            R.id.ll_fg_color_layout_text -> {
                mCurType = TYPE_FG_COLOR
            }
            R.id.ll_margin_layout_text -> {
                mCurType = TYPE_MARGIN
                showMarginDlg(mView.marginLeft, mView.marginTop,
                        mView.marginRight, mView.marginBottom)
            }
            R.id.ll_padding_layout_text -> {
                mCurType = TYPE_PADDING
                showMarginDlg(mView.paddingLeft, mView.paddingTop,
                        mView.paddingRight, mView.paddingBottom)
            }
            R.id.ll_text_style_layout_text -> {
                mCurType = TYPE_TEXT_STYLE
                showSelectDlg(TEXT_STYLE_NAME, TEXT_STYLE_VALUE.indexOf(mView.textStyle))
            }
            R.id.ll_gravity_layout_text -> {
                mCurType = TYPE_GRAVITY
                showSelectDlg(GRAVITY_NAME, GRAVITY_VALUE.indexOf(mView.gravity))
            }
            R.id.ll_min_line_layout_text -> {
                mCurType = TYPE_LINE_MIN
                showSelectDlg(TEXT_LINE_MIN, Math.min(TEXT_LINE_MAX, mView.maxLine), mView.minLine)
            }
            R.id.ll_max_line_layout_text -> {
                mCurType = TYPE_LINE_MAX
                showSelectDlg(Math.max(TEXT_LINE_MIN, mView.minLine), TEXT_LINE_MAX, mView.maxLine)
            }
        }
    }

    private fun toPx(dp: Int): Int {
        return DensityUtil.dp2px(mContext, dp.toFloat()).toInt()
    }

    companion object {
        val TEXT_STYLE_VALUE = intArrayOf(Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC,
                Typeface.BOLD_ITALIC)
        val TEXT_STYLE_NAME = arrayOf("正常", "粗体", "斜体", "粗斜体")

        val TEXT_SIZE_MIN = 8
        val TEXT_SIZE_MAX = 30

        val GRAVITY_VALUE = intArrayOf(Gravity.TOP or Gravity.START,
                Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                Gravity.TOP or Gravity.END, Gravity.CENTER or Gravity.START,
                Gravity.CENTER, Gravity.CENTER or Gravity.END,
                Gravity.BOTTOM or Gravity.START, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL,
                Gravity.BOTTOM or Gravity.END)
        val GRAVITY_NAME = arrayOf("上左", "上中", "上右", "中左", "中", "中右", "下左", "下中", "下右")

        val TEXT_LINE_MIN = 1
        val TEXT_LINE_MAX = 5

        val TYPE_TEXT = 1
        val TYPE_TEXT_SIZE = 2
        val TYPE_TEXT_COLOR = 3
        val TYPE_BG_COLOR = 4
        val TYPE_FG_COLOR = 5
        val TYPE_MARGIN = 6
        val TYPE_PADDING = 7
        val TYPE_TEXT_STYLE = 8
        val TYPE_GRAVITY = 9
        val TYPE_LINE_MIN = 10
        val TYPE_LINE_MAX = 11
    }
}
