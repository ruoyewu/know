package com.wuruoye.know.ui.edit.controller

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.TextInputLayout
import android.support.v4.app.ActivityCompat
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import com.wuruoye.know.R
import com.wuruoye.know.widget.BottomAlertDialog
import com.wuruoye.know.widget.ColorPickerView
import com.wuruoye.know.widget.MarginPickerView
import com.wuruoye.library.util.DensityUtil

/**
 * Created at 2019/3/18 17:16 by wuruoye
 * Description:
 */
abstract class AbstractEditorController : EditorController,
        MarginPickerView.OnMarginChangedListener {
    protected lateinit var mContext: Context

    private lateinit var dlgEdit: Dialog
    private lateinit var tilEdit: TextInputLayout
    private lateinit var etEdit: EditText

    private lateinit var dlgMargin: Dialog
    private lateinit var tvMargin: TextView
    private lateinit var tmv: MarginPickerView

    private lateinit var dlgSelect: Dialog
    private lateinit var npSelect: NumberPicker

    private lateinit var dlgColor: Dialog
    private lateinit var cpvColor: ColorPickerView

    protected var mCurType = 0

    internal fun attach(context: Context) {
        mContext = context

        initDlg()
    }

    @SuppressLint("InflateParams")
    private fun initDlg() {
        // margin change view
        val marginView = LayoutInflater.from(mContext)
                .inflate(R.layout.dlg_margin, null)
        tmv = marginView.findViewById(R.id.tmv_dlg_margin)
        tvMargin = marginView.findViewById(R.id.tv_dlg_margin)
        tmv.setOnMarginChangedListener(this)

        dlgMargin = BottomAlertDialog.Builder(mContext)
                .setContentView(marginView)
                .setConfirmListener(View.OnClickListener {
                    dlgMargin.dismiss()
                    val margin = tmv.margin
                    onMarginSubmit(margin[0], margin[1], margin[2], margin[3])
                }, Gravity.TOP)
                .build()

        // edit view
        tilEdit = LayoutInflater.from(mContext)
                .inflate(R.layout.dlg_edit, null) as TextInputLayout
        etEdit = tilEdit.findViewById(R.id.et_dlg_edit)
        dlgEdit = BottomAlertDialog.Builder(mContext)
                .setConfirmListener(View.OnClickListener {
                    val text = etEdit.text.toString()
                    if (text.isEmpty()) {
                        tilEdit.error = "输入不能为空"
                    } else {
                        etEdit.text.clear()
                        dlgEdit.dismiss()
                        onEditSubmit(text)
                    }
                })
                .setCancelListener(View.OnClickListener {
                    dlgEdit.dismiss()
                    etEdit.text.clear()
                })
                .setContentView(tilEdit)
                .build()

        // select view
        npSelect = LayoutInflater.from(mContext)
                .inflate(R.layout.dlg_number_picker, null) as NumberPicker
        // change divider color
        changePickerDivider(npSelect)
        dlgSelect = BottomAlertDialog.Builder(mContext)
                .setContentView(npSelect)
                .setConfirmListener(View.OnClickListener {
                    dlgSelect.dismiss()
                    onItemSelect(npSelect.value)
                }, Gravity.TOP)
                .build()

        // color view
        cpvColor = ColorPickerView(mContext)
        dlgColor = BottomAlertDialog.Builder(mContext)
                .setContentView(cpvColor)
                .setConfirmListener(View.OnClickListener {
                    dlgColor.dismiss()
                    onColorSubmit(cpvColor.getColor())
                }, Gravity.TOP)
                .build()
    }

    private fun changePickerDivider(picker: NumberPicker) {
        try {
            val fields = picker.javaClass.declaredFields
            for (f in fields) {
                if (f.name == "mSelectionDivider") {
                    f.isAccessible = true
                    val drawable = ColorDrawable(ActivityCompat
                            .getColor(mContext, R.color.transparent))
                    f.set(picker, drawable)
                    break
                }
            }
        } catch (e: Exception) {}
    }

    @SuppressLint("SetTextI18n")
    override fun onMarginChanged(left: Int, top: Int, right: Int, bottom: Int) {
        tvMargin.text = "$left | $top | $right | $bottom"
    }

    protected fun showMarginDlg(left: Int, top: Int, right: Int, bottom: Int) {
        tmv.setMargin(left, top, right, bottom)
        dlgMargin.show()
    }

    protected fun showEditDlg(hint: String, type: Int) {
        tilEdit.hint = hint
        if (type > 0) {
            etEdit.inputType = type
        }
        tilEdit.isErrorEnabled = false
        etEdit.clearFocus()
        dlgEdit.show()
    }

    protected fun showSelectDlg(name: Array<String>, select: Int) {
        npSelect.displayedValues = null
        npSelect.minValue = 0
        npSelect.maxValue = name.size-1
        npSelect.displayedValues = name
        npSelect.value = select
        dlgSelect.show()
    }

    protected fun showSelectDlg(min: Int, max: Int, select: Int) {
        npSelect.displayedValues = null
        npSelect.minValue = min
        npSelect.maxValue = max
        npSelect.value = select
        dlgSelect.show()
    }

    protected fun showColorDlg(color: Int) {
        cpvColor.setColor(color)
        dlgColor.show()
    }

    protected fun showLengthDlg(length: Int) {
        showSelectDlg(LENGTH_NAME, if (length < 0) LENGTH_VALUE.indexOf(length)
                                    else LENGTH_VALUE.size-1)
    }

    protected fun toPx(dp: Int): Int {
        return DensityUtil.dp2px(mContext, dp.toFloat()).toInt()
    }

    protected fun lengthToPx(length: Int): Int {
        return if (length < 0) length
                else DensityUtil.dp2px(mContext, length.toFloat()).toInt()
    }

    protected abstract fun onMarginSubmit(left: Int, top: Int, right: Int, bottom: Int)

    protected open fun onEditSubmit(text: String) {
        when (mCurType) {
            TYPE_HEIGHT,
            TYPE_WIDTH -> {
                onLengthSubmit(text.toInt())
            }
        }
    }

    protected open fun onItemSelect(value: Int) {
        when (mCurType) {
            TYPE_HEIGHT,
            TYPE_WIDTH -> {
                if (value < 2) {
                    onLengthSubmit(LENGTH_VALUE[value])
                } else {
                    showEditDlg("输入数字", InputType.TYPE_CLASS_NUMBER)
                }
            }
        }
    }

    protected abstract fun onColorSubmit(color: Int)

    protected abstract fun onLengthSubmit(length: Int)

    protected fun length2String(length: Int): String {
        return if (length < 0) {
            LENGTH_NAME[LENGTH_VALUE.indexOf(length)]
        } else {
            length.toString()
        }
    }

    protected fun margin2String(left: Int, top: Int, right: Int, bottom: Int): String {
        return "$left | $top | $right | $bottom"
    }

    override fun recycler() {
        tmv.recycler()
    }

    companion object {
        val TEXT_STYLE_VALUE = intArrayOf(Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC,
                Typeface.BOLD_ITALIC)
        val TEXT_STYLE_NAME = arrayOf("正常", "粗体", "斜体", "粗斜体")

        const val TEXT_SIZE_MIN = 8
        const val TEXT_SIZE_MAX = 30

        val GRAVITY_VALUE = intArrayOf(Gravity.TOP or Gravity.START,
                Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                Gravity.TOP or Gravity.END, Gravity.CENTER or Gravity.START,
                Gravity.CENTER, Gravity.CENTER or Gravity.END,
                Gravity.BOTTOM or Gravity.START, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL,
                Gravity.BOTTOM or Gravity.END)
        val GRAVITY_NAME = arrayOf("上左", "上中", "上右", "中左", "中", "中右", "下左", "下中", "下右")

        val INPUT_TYPE_VALUE = intArrayOf(InputType.TYPE_TEXT_FLAG_MULTI_LINE,
                InputType.TYPE_CLASS_NUMBER,
                InputType.TYPE_CLASS_PHONE, InputType.TYPE_CLASS_DATETIME)
        val INPUT_TYPE_NAME = arrayOf("文本", "数字", "电话", "日期")

        const val TEXT_LINE_MIN = 1
        const val TEXT_LINE_MAX = 5

        val LENGTH_VALUE = intArrayOf(-2, -1, 0)
        val LENGTH_NAME = arrayOf("自适应", "铺满", "自定义")

        val ORIENTATION_VALUE = intArrayOf(LinearLayout.VERTICAL, LinearLayout.HORIZONTAL)
        val ORIENTATION_NAME = arrayOf("纵向", "横向")

        const val TYPE_TEXT = 1
        const val TYPE_TEXT_SIZE = 2
        const val TYPE_TEXT_COLOR = 3
        const val TYPE_BG_COLOR = 4
        const val TYPE_FG_COLOR = 5
        const val TYPE_MARGIN = 6
        const val TYPE_PADDING = 7
        const val TYPE_TEXT_STYLE = 8
        const val TYPE_GRAVITY = 9
        const val TYPE_LINE_MIN = 10
        const val TYPE_LINE_MAX = 11
        const val TYPE_HINT = 12
        const val TYPE_HINT_SIZE = 13
        const val TYPE_HINT_COLOR = 14
        const val TYPE_INPUT_TYPE = 15
        const val TYPE_WIDTH = 16
        const val TYPE_HEIGHT = 17
        const val TYPE_ORIENTATION = 18
    }
}
