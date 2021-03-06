package com.wuruoye.know.ui.edit.controller

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.wuruoye.know.R
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.util.ColorUtil

/**
 * Created at 2019/3/27 18:28 by wuruoye
 * Description:
 */
class EditTextController(private val mView: RecordTextView) : AbstractEditorController(),
        View.OnClickListener {
    private lateinit var svOptions: ScrollView
    private lateinit var flContent: FrameLayout

    private lateinit var mShowLayout: TextInputLayout
    private lateinit var mShowView: EditText
    private lateinit var llTextSize: LinearLayout
    private lateinit var tvTextSize: TextView
    private lateinit var llTextColor: LinearLayout
    private lateinit var tvTextColor: TextView
    private lateinit var llHint: LinearLayout
    private lateinit var tvHint: TextView
    private lateinit var llHintSize: LinearLayout
    private lateinit var tvHintSize: TextView
    private lateinit var llHintColor: LinearLayout
    private lateinit var tvHintColor: TextView
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
    private lateinit var llInputType: LinearLayout
    private lateinit var tvInputType: TextView

    override val result: RecordView
        get() = mView

    override fun attach(context: Context, fl: FrameLayout, sv: ScrollView) {
        super.attach(context)
        this.flContent = fl
        this.svOptions = sv

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        mShowLayout = ViewFactory.generateView(mContext, mView, flContent) as TextInputLayout
        mShowView = mShowLayout.findViewById(R.id.et_view_edit)
        LayoutInflater.from(mContext)
                .inflate(R.layout.layout_edit_text, svOptions)

        with(svOptions) {
            llTextSize = findViewById(R.id.ll_text_size_layout_edit)
            llTextColor = findViewById(R.id.ll_text_color_layout_edit)
            llHint = findViewById(R.id.ll_hint_layout_edit)
            llHintSize = findViewById(R.id.ll_hint_size_layout_edit)
            llHintColor = findViewById(R.id.ll_hint_color_layout_edit)
            llBgColor = findViewById(R.id.ll_bg_color_layout_edit)
            llFgColor = findViewById(R.id.ll_fg_color_layout_edit)
            llMargin = findViewById(R.id.ll_margin_layout_edit)
            llPadding = findViewById(R.id.ll_padding_layout_edit)
            llTextStyle = findViewById(R.id.ll_text_style_layout_edit)
            llGravity = findViewById(R.id.ll_gravity_layout_edit)
            llInputType = findViewById(R.id.ll_input_type_layout_edit)
            llMinLine = findViewById(R.id.ll_min_line_layout_edit)
            llMaxLine = findViewById(R.id.ll_max_line_layout_edit)
            llWidth = findViewById(R.id.ll_width_layout_edit)
            llHeight = findViewById(R.id.ll_height_layout_edit)

            tvTextSize = findViewById(R.id.tv_text_size_layout_edit)
            tvTextColor = findViewById(R.id.tv_text_color_layout_edit)
            tvHint = findViewById(R.id.tv_hint_layout_edit)
            tvHintSize = findViewById(R.id.tv_hint_size_layout_edit)
            tvHintColor = findViewById(R.id.tv_hint_color_layout_edit)
            tvMargin = findViewById(R.id.tv_margin_layout_edit)
            tvPadding = findViewById(R.id.tv_padding_layout_edit)
            tvTextStyle = findViewById(R.id.tv_text_style_layout_edit)
            tvBgColor = findViewById(R.id.tv_bg_color_layout_edit)
            tvFgColor = findViewById(R.id.tv_fg_color_layout_edit)
            tvGravity = findViewById(R.id.tv_gravity_layout_edit)
            tvInputType = findViewById(R.id.tv_input_type_layout_edit)
            tvMinLine = findViewById(R.id.tv_min_line_layout_edit)
            tvMaxLine = findViewById(R.id.tv_max_line_layout_edit)
            tvWidth = findViewById(R.id.tv_width_layout_edit)
            tvHeight = findViewById(R.id.tv_height_layout_edit)
        }

        llTextSize.setOnClickListener(this)
        llTextColor.setOnClickListener(this)
        llHint.setOnClickListener(this)
        llHintColor.setOnClickListener(this)
        llHintSize.setOnClickListener(this)
        llBgColor.setOnClickListener(this)
        llFgColor.setOnClickListener(this)
        llTextStyle.setOnClickListener(this)
        llGravity.setOnClickListener(this)
        llInputType.setOnClickListener(this)
        llMinLine.setOnClickListener(this)
        llMaxLine.setOnClickListener(this)

        with(mView) {
            tvTextSize.text = textSize.toString()
            tvTextColor.text = ColorUtil.color2hex(textColor)
            tvTextColor.setTextColor(textColor)
            tvHint.text = hint
            tvHintSize.text = hintSize.toString()
            tvHintColor.text = ColorUtil.color2hex(hintColor)
            tvHintColor.setTextColor(hintColor)
            tvBgColor.text = ColorUtil.color2hex(bgColor)
            tvFgColor.text = ColorUtil.color2hex(fgColor)
            tvTextStyle.text = TEXT_STYLE_NAME[TEXT_STYLE_VALUE.indexOf(textStyle)]
            tvGravity.text = GRAVITY_NAME[GRAVITY_VALUE.indexOf(gravity)]
            tvInputType.text = INPUT_TYPE_NAME[INPUT_TYPE_VALUE.indexOf(inputType)]
            tvMinLine.text = minLine.toString()
            tvMaxLine.text = maxLine.toString()
        }

        super.initView(mView, mShowView)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_text_size_layout_edit -> {
                mCurType = TYPE_TEXT_SIZE
                showSelectDlg(TEXT_SIZE_MIN, TEXT_SIZE_MAX, mView.textSize)
            }
            R.id.ll_text_color_layout_edit -> {
                mCurType = TYPE_TEXT_COLOR
                showColorDlg(mView.textColor)
            }
            R.id.ll_hint_layout_edit -> {
                mCurType = TYPE_HINT
                showEditDlg("输入提示内容", INPUT_TYPE_VALUE[4])
            }
            R.id.ll_hint_size_layout_edit -> {
                mCurType = TYPE_HINT_SIZE
                showSelectDlg(TEXT_SIZE_MIN, TEXT_SIZE_MAX, mView.hintSize)
            }
            R.id.ll_hint_color_layout_edit -> {
                mCurType = TYPE_HINT_COLOR
                showColorDlg(mView.hintColor)
            }
            R.id.ll_bg_color_layout_edit -> {
                mCurType = TYPE_BG_COLOR
                showColorDlg(mView.bgColor)
            }
            R.id.ll_fg_color_layout_edit -> {
                mCurType = TYPE_FG_COLOR
                showColorDlg(mView.fgColor)
            }
            R.id.ll_text_style_layout_edit -> {
                mCurType = TYPE_TEXT_STYLE
                showSelectDlg(TEXT_STYLE_NAME, TEXT_STYLE_VALUE.indexOf(mView.textStyle))
            }
            R.id.ll_gravity_layout_edit -> {
                mCurType = TYPE_GRAVITY
                showSelectDlg(GRAVITY_NAME, GRAVITY_VALUE.indexOf(mView.gravity))
            }
            R.id.ll_input_type_layout_edit -> {
                mCurType = TYPE_INPUT_TYPE
                showSelectDlg(INPUT_TYPE_NAME, INPUT_TYPE_VALUE.indexOf(mView.inputType))
            }
            R.id.ll_min_line_layout_edit -> {
                mCurType = TYPE_LINE_MIN
                showSelectDlg(TEXT_LINE_MIN, Math.min(TEXT_LINE_MAX, mView.maxLine), mView.minLine)
            }
            R.id.ll_max_line_layout_edit -> {
                mCurType = TYPE_LINE_MAX
                showSelectDlg(Math.max(TEXT_LINE_MIN, mView.minLine), TEXT_LINE_MAX, mView.maxLine)
            }
        }
    }

    override fun onEditSubmit(text: String) {
        super.onEditSubmit(text)
        when (mCurType) {
            TYPE_HINT -> {
                mView.hint = text
                mShowLayout.hint = text
                tvHint.text = text
            }
        }
    }

    override fun onItemSelect(value: Int) {
        super.onItemSelect(value)
        when (mCurType) {
            TYPE_TEXT_SIZE -> {
                mView.textSize = value
                mShowView.textSize = value.toFloat()
                tvTextSize.text = value.toString()
            }
            TYPE_HINT_SIZE -> {
                mView.hintSize = value
                tvHintSize.text = value.toString()
                // TODO set hint size in TextInputLayout
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
            TYPE_INPUT_TYPE -> {
                mView.inputType = INPUT_TYPE_VALUE[value]
                mShowView.inputType = INPUT_TYPE_VALUE[value]
                tvInputType.text = INPUT_TYPE_NAME[value]
            }
            TYPE_LINE_MAX -> {
                mView.maxLine = value
                mShowView.maxLines = value
                tvMaxLine.text = value.toString()
            }
        }
    }

    override fun onColorSubmit(color: Int) {
        when (mCurType) {
            TYPE_TEXT_COLOR -> {
                mView.textColor = color
                mShowView.setTextColor(color)
                tvTextColor.setTextColor(color)
                tvTextColor.text = ColorUtil.color2hex(color)
            }
            TYPE_HINT_COLOR -> {
                mView.hintColor = color
                // TODO set hint color in TextInputLayout
                tvHintColor.setTextColor(color)
                tvHintColor.text = ColorUtil.color2hex(color)
            }
            TYPE_BG_COLOR -> {
                mView.bgColor = color
                mShowLayout.setBackgroundColor(color)
                tvBgColor.setTextColor(color)
                tvBgColor.text = ColorUtil.color2hex(color)
            }
            TYPE_FG_COLOR -> {
                mView.fgColor = color
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mShowLayout.foreground = ColorDrawable(color)
                }
                tvFgColor.setTextColor(color)
                tvFgColor.text = ColorUtil.color2hex(color)
            }
        }
    }
}
