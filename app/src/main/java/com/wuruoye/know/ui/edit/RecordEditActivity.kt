package com.wuruoye.know.ui.edit

import android.os.Bundle

import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.ui.home.contract.RecordContract

class RecordEditActivity : ToolbarActivity<RecordContract.Presenter>() {

    private var recordType: Int = 0

    override fun getContentView(): Int {
        return 0
    }

    override fun initData(bundle: Bundle) {
        recordType = bundle.getInt(RECORD_TYPE)
    }

    override fun initView() {
        super.initView()

    }

    companion object {
        val RECORD_TYPE = "type"
    }
}
