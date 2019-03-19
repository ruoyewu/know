package com.wuruoye.know.ui.edit.controller

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.wuruoye.know.R
import com.wuruoye.know.widget.BottomDialog
import com.wuruoye.know.widget.TouchMarginView

/**
 * Created at 2019/3/18 17:16 by wuruoye
 * Description:
 */
abstract class AbstractEditorController : EditorController,
        TouchMarginView.OnMarginChangedListener,
        View.OnClickListener {
    protected lateinit var mContext: Context

    protected lateinit var dlgEdit: AlertDialog

    protected lateinit var dlgMargin: Dialog
    private var btnMargin: Button? = null
    private var tvMargin: TextView? = null
    private var tmv: TouchMarginView? = null

    internal fun attach(context: Context) {
        mContext = context

        initDlg()
    }

    private fun initDlg() {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.dlg_margin, null)
        tmv = view.findViewById(R.id.tmv_dlg_margin)
        btnMargin = view.findViewById(R.id.btn_dlg_margin)
        tvMargin = view.findViewById(R.id.tv_dlg_margin)
        tmv!!.setOnMarginChangedListener(this)
        btnMargin!!.setOnClickListener(this)
        dlgMargin = BottomDialog(mContext, view)
    }

    @SuppressLint("SetTextI18n")
    override fun onMarginChanged(left: Int, top: Int, right: Int, bottom: Int) {
        tvMargin!!.text = "$left | $top | $right | $bottom"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_dlg_margin -> {
                dlgMargin.dismiss()
                val margin = tmv!!.margin
                onMarginBtnClick(margin[0], margin[1], margin[2], margin[3])
            }
        }
    }

    protected fun setMargin(left: Int, top: Int, right: Int, bottom: Int) {
        tmv!!.setMargin(left, top, right, bottom)
    }

    protected abstract fun onMarginBtnClick(left: Int, top: Int, right: Int, bottom: Int)
}
