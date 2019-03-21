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
import com.wuruoye.know.model.ViewFactory
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
        IToolbarView.OnToolbarTitleListener {

    private lateinit var dlgTitle: BottomAlertDialog
    private lateinit var tilTitle: TextInputLayout
    private lateinit var etTitle: EditText

    private lateinit var dlgSelectItem: BottomSheetDialog
    private lateinit var rvSelectItem: RecyclerView

    private lateinit var mType: RecordType

    override fun getContentView(): Int {
        return R.layout.activity_record_type_edit
    }

    override fun initData(bundle: Bundle?) {
        val type = try {
            bundle!!.getParcelable<RecordType>(RECORD_TYPE)
        } catch (ignore: Exception) {
            null
        }
        mType = type ?: RecordType(-1, "", arrayListOf(), -1, -1)


        setPresenter(RecordTypeEditPresenter())
    }

    override fun initView() {
        super.initView()
        setToolbarBackListener(this)

        fab_record_type_edit.setOnClickListener(this)

        setToolbarTitleListener(this)
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
            ll_record_type_edit.addView(v)
            v!!.setOnClickListener { }
        }
    }

    private fun initTitle() {
        if (mType.title!!.isEmpty()) {
            dlgTitle.show()
        } else {
            setToolbarTitle(mType.title!!)
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

    override fun onTitleClick() {
        etTitle.setText(mType.title)
        etTitle.setSelection(mType.title!!.length)
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
                setTitle(etTitle.text.toString())
            }
        }
    }

    override fun onItemClick(recordTypeItem: RecordTypeItem) {
        dlgSelectItem.dismiss()
        when (recordTypeItem.type) {
            RecordTypeItem.TYPE_TEXT -> {
                val intent = Intent(this, TypeItemEditActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(TypeItemEditActivity.RECORD_TYPE, RecordTypeItem.TYPE_TEXT)
                intent.putExtras(bundle)
                startActivityForResult(intent, FOR_RESULT)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FOR_RESULT && resultCode == Activity.RESULT_OK) {
            val view = data!!.getParcelableExtra<RecordView>(TypeItemEditActivity.RECORD_VIEW)
            mType.views.add(view)
            ll_record_type_edit.addView(ViewFactory.generateView(this, view,
                    ll_record_type_edit))
        }
    }

    companion object {
        const val RECORD_TYPE = "type"
        const val FOR_RESULT = 1
    }
}
