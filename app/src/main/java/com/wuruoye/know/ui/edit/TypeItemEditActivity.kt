package com.wuruoye.know.ui.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.FrameLayout
import android.widget.ScrollView

import com.wuruoye.know.R
import com.wuruoye.know.base.IToolbarView
import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.ui.edit.contract.TypeItemEditContract
import com.wuruoye.know.ui.edit.controller.EditorController
import com.wuruoye.know.ui.edit.presenter.TypeItemEditPresenter

class TypeItemEditActivity : ToolbarActivity<TypeItemEditContract.Presenter>(),
        TypeItemEditContract.View, IToolbarView.OnToolbarMoreListener {

    private var mView: RecordView? = null
    private var mType: Int = 0
    private var mController: EditorController? = null

    private var flContent: FrameLayout? = null
    private var svOptions: ScrollView? = null

    override fun getContentView(): Int {
        return R.layout.activity_type_item_edit
    }

    override fun initData(bundle: Bundle) {
        try {
            mView = bundle.getParcelable<Parcelable>(RECORD_VIEW) as RecordView
        } catch (ignore: Exception) {
            mType = bundle.getInt(RECORD_TYPE)
        }

        setPresenter(TypeItemEditPresenter())
    }

    override fun initView() {
        super.initView()

        mController = mPresenter.generateController(mType, mView)
        flContent = findViewById(R.id.fl_type_item_edit)
        svOptions = findViewById(R.id.sv_type_item_edit)
        mController!!.attach(this, flContent!!, svOptions!!)

        setToolbarMoreListener(this)
        setToolbarBack(R.drawable.ic_left, "")
        setToolbarMore(R.drawable.ic_check, "")
    }

    override fun onMoreClick() {
        val view = mController!!.result
        val intent = Intent()
        intent.putExtra(RECORD_VIEW, view as Parcelable)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        val RECORD_TYPE = "type"
        val RECORD_VIEW = "view"
    }
}
