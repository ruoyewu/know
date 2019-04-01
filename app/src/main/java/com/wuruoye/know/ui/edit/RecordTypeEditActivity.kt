package com.wuruoye.know.ui.edit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.wuruoye.know.R
import com.wuruoye.know.base.IToolbarView
import com.wuruoye.know.base.ToolbarActivity
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
        IToolbarView.OnToolbarTitleListener, IToolbarView.OnToolbarMoreListener {

    private lateinit var dlgTitle: BottomAlertDialog
    private lateinit var tilTitle: TextInputLayout
    private lateinit var etTitle: EditText

    private lateinit var dlgSelectItem: BottomSheetDialog
    private lateinit var rvSelectItem: RecyclerView

    private lateinit var mType: RecordType
    private lateinit var mUpdateRecordView: RecordView

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
                .inflate(R.layout.dlg_select_type, null) as RecyclerView
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
            val v = mPresenter.generateView(this, view, ll_record_type_edit)
            if (v is TextInputLayout) v.findViewById<EditText>(R.id.et_view_edit)
                    .setOnLongClickListener { onViewClick(view)
                    true }
            else
                v?.setOnLongClickListener { onViewClick(view)
                true }
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
        if (mType.createTime > 0) mType.updateTime = System.currentTimeMillis()
        else mType.createTime = System.currentTimeMillis()
        mPresenter.saveRecordType(this, mType)
        finish()
    }

    override fun onTitleClick() {
        etTitle.setText(mType.title)
        etTitle.setSelection(mType.title.length)
        dlgTitle.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_record_type_edit -> showSelectDlg()
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

    override fun onViewClick(recordView: RecordView) {
        mUpdateRecordView = recordView
        val intent = Intent(this, TypeItemEditActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(TypeItemEditActivity.RECORD_VIEW, recordView)
        intent.putExtras(bundle)
        startActivityForResult(intent, FOR_UPDATE_RESULT)
    }

    override fun onItemClick(recordTypeItem: RecordTypeItem) {
        dlgSelectItem.dismiss()
        val intent = Intent(this, TypeItemEditActivity::class.java)
        intent.putExtra(TypeItemEditActivity.RECORD_TYPE, recordTypeItem.type)
        startActivityForResult(intent, FOR_ADD_RESULT)
    }

    override fun onViewBack(view: RecordView, type: Int) {
        var v: View? = null
        when (type) {
            FOR_ADD_RESULT -> {
                v = mPresenter.generateView(this, view, ll_record_type_edit)
                mType.views.add(view)
            }
            FOR_UPDATE_RESULT -> {
                val index = mType.views.indexOf(mUpdateRecordView)
                mType.views.removeAt(index)
                ll_record_type_edit.removeViewAt(index)
                mType.views.add(index, view)
                v = mPresenter.generateView(this, view, ll_record_type_edit, false)
                ll_record_type_edit.addView(v, index)
            }
        }
        v?.setOnClickListener { onViewClick(view) }
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
    }
}
