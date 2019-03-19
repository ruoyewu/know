package com.wuruoye.know.ui.edit.controller

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.TextInputLayout
import android.support.v4.app.ActivityCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import com.wuruoye.know.R
import com.wuruoye.know.widget.BottomAlertDialog
import com.wuruoye.know.widget.TouchMarginView

/**
 * Created at 2019/3/18 17:16 by wuruoye
 * Description:
 */
abstract class AbstractEditorController : EditorController,
        TouchMarginView.OnMarginChangedListener {
    protected lateinit var mContext: Context

    private lateinit var dlgEdit: Dialog
    private lateinit var tilEdit: TextInputLayout
    private lateinit var etEdit: EditText

    private lateinit var dlgMargin: Dialog
    private lateinit var btnMargin: Button
    private lateinit var tvMargin: TextView
    private lateinit var tmv: TouchMarginView

    private lateinit var dlgSelect: Dialog
    private lateinit var npSelect: NumberPicker

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

        npSelect = LayoutInflater.from(mContext)
                .inflate(R.layout.dlg_picker, null) as NumberPicker
        // change divider color
        changePickerDivider(npSelect)
        dlgSelect = BottomAlertDialog.Builder(mContext)
                .setContentView(npSelect)
                .setConfirmListener(View.OnClickListener {
                    dlgSelect.dismiss()
                    onItemSelect(npSelect.value)
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
        npSelect.minValue = 0;
        npSelect.maxValue = name.size-1
        npSelect.displayedValues = name
        npSelect.value = select
        dlgSelect.show()
    }

    protected fun showSelectDlg(min: Int, max: Int, select: Int) {
        npSelect.displayedValues = null
        npSelect.minValue = min;
        npSelect.maxValue = max;
        npSelect.value = select;
        dlgSelect.show()
    }

    protected abstract fun onMarginSubmit(left: Int, top: Int, right: Int, bottom: Int)

    protected abstract fun onEditSubmit(text: String)

    protected abstract fun onItemSelect(value: Int)
}
