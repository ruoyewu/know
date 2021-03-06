package com.wuruoye.know.ui.edit

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.wuruoye.know.R
import com.wuruoye.know.base.IToolbarView
import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.RecordLayoutView
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.model.beans.RecordTypeItem
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.ui.edit.adapter.SelectItemAdapter
import com.wuruoye.know.ui.edit.contract.RecordTypeEditContract
import com.wuruoye.know.ui.edit.presenter.RecordTypeEditPresenter
import com.wuruoye.know.widget.BottomAlertDialog
import com.wuruoye.library.adapter.WBaseRVAdapter
import kotlinx.android.synthetic.main.activity_record_type_edit.*

class RecordTypeEditActivity : ToolbarActivity<RecordTypeEditContract.Presenter>(),
        RecordTypeEditContract.View, IToolbarView.OnToolbarBackListener,
        View.OnClickListener, WBaseRVAdapter.OnItemClickListener<RecordTypeItem>,
        IToolbarView.OnToolbarTitleListener, IToolbarView.OnToolbarMoreListener,
        ViewFactory.OnLongClickListener {

    private lateinit var dlgTitle: BottomAlertDialog
    private lateinit var tilTitle: TextInputLayout
    private lateinit var etTitle: EditText

    private lateinit var dlgSelectItem: BottomSheetDialog
    private lateinit var rvSelectItem: RecyclerView

    private lateinit var mType: RecordType
    private lateinit var mUpdateRecordView: RecordView

    private lateinit var mParent: ViewGroup
    private lateinit var mViews: ArrayList<RecordView>

    override fun getContentView(): Int {
        return R.layout.activity_record_type_edit
    }

    override fun initData(bundle: Bundle?) {
        setPresenter(RecordTypeEditPresenter())

        val type = try {
            bundle!!.getParcelable<RecordType>(RECORD_TYPE)
        } catch (ignore: Exception) {
            mPresenter.getDefaultRecordType()
        }
        mType = if (type.id < 0) type else mPresenter.getRecordType(this, type.id)
    }

    override fun initView() {
        super.initView()
        setToolbarBackListener(this)

        fab_record_type_edit.setOnClickListener(this)

        setToolbarTitleListener(this)
        setToolbarBackListener(this)
        setToolbarMoreListener(this)
        setToolbarBack(R.drawable.ic_left, "")
        setToolbarMore(R.drawable.ic_check, "")

        initDlg()
        initItems()
        initTitle()
    }

    @SuppressLint("InflateParams")
    private fun initDlg() {
        tilTitle = LayoutInflater.from(this)
                .inflate(R.layout.dlg_edit, null) as TextInputLayout
        tilTitle.hint = "记录类型名称"
        etTitle = tilTitle.findViewById(R.id.et_dlg_edit)
        etTitle.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                setTitle(etTitle.text.toString())
                dlgTitle.dismiss()
            }
            false
        }
        dlgTitle = BottomAlertDialog.Builder(this)
                .setContentView(tilTitle)
                .setConfirmListener(this)
                .setCancelListener(this)
                .setCancelable(false)
                .build()

        rvSelectItem = LayoutInflater.from(this)
                .inflate(R.layout.dlg_record_type, null) as RecyclerView
        val adapter = SelectItemAdapter()
        adapter.data = mPresenter.selectItems
        adapter.setOnItemClickListener(this)
        rvSelectItem.adapter = adapter
        rvSelectItem.layoutManager = LinearLayoutManager(this)
        dlgSelectItem = BottomSheetDialog(this)
        dlgSelectItem.setContentView(rvSelectItem)
    }

    private fun initItems() {
        ll_record_type_edit.removeAllViews()
        for (view in mType.views) {
            mPresenter.generateView(this, view,
                    ll_record_type_edit, mType.views, true, this)
        }
    }

    private fun initTitle() {
        if (mType.id < 0) {
            dlgTitle.show()
        } else {
            setToolbarTitle(mType.title)
        }
    }

    private fun setTitle(title: String) {
        setToolbarTitle(title)
        mType.title = title
    }

    override fun showSelectDlg() {
        dlgSelectItem.show()
    }

    override fun onBackClick() {
        onBackPressed()
    }

    override fun onMoreClick() {
        mPresenter.saveRecordType(this, mType)
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onTitleClick() {
        etTitle.setText(mType.title)
        etTitle.setSelection(mType.title.length)
        dlgTitle.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_record_type_edit -> {
                mParent = ll_record_type_edit
                mViews = mType.views
                showSelectDlg()
            }
            R.id.btn_cancel_dlg_bottom_alert -> {
                finish()
            }
            R.id.btn_confirm_dlg_bottom_alert -> {
                dlgTitle.dismiss()
                if (!etTitle.text.isEmpty()) {
                    setTitle(etTitle.text.toString())
                }
            }
        }
    }

    override fun onLongClick(recordView: RecordView, view: View,
                             parentView: ArrayList<RecordView>, parent: ViewGroup) {
        AlertDialog.Builder(this)
                .setItems(if (recordView is RecordLayoutView) ITEM_LAYOUT else ITEM_VIEW) {
                    _, which ->
                        when (which) {
                            0 -> {      // update
                                mUpdateRecordView = recordView
                                mParent = parent
                                mViews = parentView

                                val intent = Intent(this,
                                        TypeItemEditActivity::class.java)
                                if (recordView is RecordLayoutView) {
                                    intent.putExtra(TypeItemEditActivity.RECORD_VIEW,
                                            RecordLayoutView(recordView))
                                } else {
                                    intent.putExtra(TypeItemEditActivity.RECORD_VIEW, recordView)
                                }
                                startActivityForResult(intent, FOR_UPDATE_RESULT)
                            }
                            1 -> {      // delete
                                parent.removeView(view)
                                parentView.remove(recordView)
                                mPresenter.removeRecordTypeItem(this, recordView)
                            }
                            2 -> {      // add
                                mParent = view as ViewGroup
                                mViews = (recordView as RecordLayoutView).views
                                showSelectDlg()
                            }
                        }
                }
                .show()
    }

    override fun onItemClick(recordTypeItem: RecordTypeItem) {
        dlgSelectItem.dismiss()
        val intent = Intent(this, TypeItemEditActivity::class.java)
        intent.putExtra(TypeItemEditActivity.RECORD_TYPE, recordTypeItem.type)
        startActivityForResult(intent, FOR_ADD_RESULT)
    }

    override fun onViewBack(view: RecordView, type: Int) {
        val v: View?
        when (type) {
            FOR_ADD_RESULT -> {
                mPresenter.generateView(this, view, mParent,
                        mViews, true, this)
                mViews.add(view)
            }
            FOR_UPDATE_RESULT -> {
                if (view is RecordLayoutView) {
                    view.views = (mUpdateRecordView as RecordLayoutView).views
                }
                val index = mViews.indexOf(mUpdateRecordView)

                mViews.removeAt(index)
                mParent.removeViewAt(index)

                v = mPresenter.generateView(this, view, mParent,
                        mViews, false, this)
                mParent.addView(v, index)
                mViews.add(index, view)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val view = data!!.getParcelableExtra<RecordView>(TypeItemEditActivity.RECORD_VIEW)
            onViewBack(view, requestCode)
        }
    }

    companion object {
        const val RECORD_TYPE = "type"
        const val FOR_ADD_RESULT = 1
        const val FOR_UPDATE_RESULT = 2

        val ITEM_VIEW = arrayOf("修改控件", "删除控件")
        val ITEM_LAYOUT = arrayOf("修改控件", "删除控件", "增加子控件")
    }
}
