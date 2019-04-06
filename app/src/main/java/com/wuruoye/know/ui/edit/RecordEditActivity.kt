package com.wuruoye.know.ui.edit

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.wuruoye.know.R
import com.wuruoye.know.base.IToolbarView
import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.model.beans.Record
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.ui.edit.contract.RecordEditContract
import com.wuruoye.know.ui.edit.presenter.RecordEditPresenter
import kotlinx.android.synthetic.main.activity_record_edit.*

/**
 * 两种进入方式：
 * 1 点击记录类型进入新增模式
 * 2 点击记录进入更改模式
 */

class RecordEditActivity : ToolbarActivity<RecordEditContract.Presenter>(),
        RecordEditContract.View,
        IToolbarView.OnToolbarBackListener, IToolbarView.OnToolbarMoreListener {
    private lateinit var mRecordType: RecordType
    private lateinit var mRecord: Record
    private lateinit var llContent: LinearLayout

    override fun getContentView(): Int {
        return R.layout.activity_record_edit
    }

    override fun initData(bundle: Bundle) {
        setPresenter(RecordEditPresenter())

        try {
            mRecord = bundle.getParcelable<Record>(RECORD)!!
            mRecordType = mPresenter.getRecordType(this, mRecord.type)
        } catch (e: Exception) {
            val type = bundle.getInt(RECORD_TYPE)
            mRecordType = mPresenter.getRecordType(this, type)
            mRecord = mPresenter.generateRecord(this, type)
        }
    }

    override fun initView() {
        super.initView()

        initToolbar()

        llContent = ll_record_edit
        mPresenter.loadRecord(this, mRecord, mRecordType, llContent)
    }

    private fun initToolbar() {
        setToolbarTitle(mRecordType.title)
        setToolbarBack(R.drawable.ic_left, "")
        setToolbarMore(R.drawable.ic_check, "")
        setToolbarBackListener(this)
        setToolbarMoreListener(this)
    }

    private fun initDlg() {

    }

    override fun onBackClick() {
        onBackPressed()
    }

    override fun onMoreClick() {
        if (mPresenter.saveRecord(this, mRecord, mRecordType, llContent)) {
            setResult(Activity.RESULT_OK)
            finish()
        } else {

        }
    }

    override fun getViewInfo(recordView: RecordView, view: View): String {
        if (recordView is RecordTextView && recordView.isEditable) {
            return view.findViewById<EditText>(R.id.et_view_edit).text.toString()
        }
        return ""
    }

    override fun setViewInfo(recordView: RecordView, view: View, info: String) {
        if (recordView is RecordTextView && recordView.isEditable) {
            val til = view as TextInputLayout
            val et = til.editText!!
            et.setText(info)
            et.setSelection(info.length)
        }
    }

    companion object {
        const val RECORD_TYPE = "type"
        const val RECORD = "record"
    }
}
