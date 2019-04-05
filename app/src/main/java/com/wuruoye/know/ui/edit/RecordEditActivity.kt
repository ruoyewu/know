package com.wuruoye.know.ui.edit

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.wuruoye.know.R
import com.wuruoye.know.base.IToolbarView

import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.Record
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.ui.edit.contract.RecordEditContract
import com.wuruoye.know.ui.edit.presenter.RecordEditPresenter
import com.wuruoye.know.util.sql.SqlUtil
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
            val type = bundle.getInt(RECORD_TYPE)
            mRecordType = mPresenter.getRecordType(this, type)
            mRecord = mPresenter.generateRecord(this, type)
        } catch (e: Exception) {
            val id = bundle.getInt(RECORD_ID)
            mRecord = mPresenter.getRecord(this, id)
            mRecordType = mPresenter.getRecordType(this, mRecord.type)
        }
    }

    override fun initView() {
        super.initView()

        initToolbar()

        llContent = ll_record_edit
        for (i in 0 until mRecordType.views.size) {
            val view = mRecordType.views[i]
            val v = ViewFactory.generateView(this, view, llContent)
            if (mRecord.items.isNotEmpty()) {
                setViewInfo(view, v!!, mRecord.items[i])
            }
        }
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
        val result = mRecord.items
        for (i in 0 until mRecordType.views.size) {
            val v = llContent.getChildAt(i)
            val view = mRecordType.views[i]
            result.add(getViewInfo(view, v))
        }

        if (mRecord.createTime > 0) {
            mRecord.updateTime = System.currentTimeMillis()
        } else {
            mRecord.createTime = System.currentTimeMillis()
        }

        if (SqlUtil.getInstance(this).saveRecord(mRecord)) {
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
            view.findViewById<EditText>(R.id.et_view_edit).setText(info)
        }
    }

    companion object {
        const val RECORD_TYPE = "type"
        const val RECORD_ID = "record"
    }
}
