package com.wuruoye.know.ui.edit

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.wuruoye.know.R
import com.wuruoye.know.base.IToolbarView
import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.*
import com.wuruoye.know.ui.edit.contract.RecordEditContract
import com.wuruoye.know.ui.edit.presenter.RecordEditPresenter
import com.wuruoye.library.util.media.IWPhoto
import com.wuruoye.library.util.media.WPhoto
import kotlinx.android.synthetic.main.activity_record_edit.*

/**
 * 两种进入方式：
 * 1 点击记录类型进入新增模式
 * 2 点击记录进入更改模式
 */

class RecordEditActivity : ToolbarActivity<RecordEditContract.Presenter>(),
        RecordEditContract.View,
        IToolbarView.OnToolbarBackListener, IToolbarView.OnToolbarMoreListener,
        ViewFactory.OnClickListener, IWPhoto.OnWPhotoListener<String> {
    private lateinit var mRecordType: RecordType
    private lateinit var mRecord: Record
    private lateinit var llContent: LinearLayout

    private lateinit var mPhotoGet: WPhoto
    private lateinit var mView: RecordView
    private lateinit var mImageView: ImageView

    override fun getContentView(): Int {
        return R.layout.activity_record_edit
    }

    override fun initData(bundle: Bundle) {
        setPresenter(RecordEditPresenter())
        mPhotoGet = WPhoto(this)

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

        for (v in mRecordType.views) {
            ViewFactory.generateView(this, v, llContent, true, this)
        }
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

    override fun onClick(recordView: RecordView, view: View) {
        if (recordView is RecordImageView) {
            mImageView = view as ImageView
            mView = recordView
            AlertDialog.Builder(this)
                    .setTitle("选择图片")
                    .setItems(ITEM_PHOTO) { _, which ->
                        when (which) {
                            0 -> {      // choose
                                mPhotoGet.choosePhoto(this)
                            }
                            1 -> {      // take
                                mPhotoGet.takePhoto(mPresenter.generateImgPath(), this)
                            }
                            2 -> {      // choose and crop
                                mPhotoGet.choosePhoto(mPresenter.generateImgPath(),
                                        1, 1, 500, 500, this)
                            }
                            3 -> {      // take and crop
                                mPhotoGet.takePhoto(mPresenter.generateImgPath(),
                                        1, 1, 500, 500, this)
                            }
                        }
                    }
                    .show()
        }
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

    override fun onPhotoResult(path: String?) {
        mPresenter.loadImg(path!!, mImageView, mView as RecordImageView)
    }

    override fun onPhotoError(error: String?) {

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

        val ITEM_PHOTO = arrayOf("相册选择", "相机拍照", "相册选择&剪裁", "相机拍照&剪裁")
    }
}
