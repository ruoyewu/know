package com.wuruoye.know.ui.edit

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import com.wuruoye.know.R
import com.wuruoye.know.base.IToolbarView
import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.model.beans.RecordTypeItem
import com.wuruoye.know.ui.edit.adapter.SelectItemAdapter
import com.wuruoye.know.ui.edit.contract.RecordTypeEditContract
import com.wuruoye.know.ui.edit.presenter.RecordTypeEditPresenter
import com.wuruoye.library.adapter.WBaseRVAdapter
import java.util.*

class RecordTypeEditActivity : ToolbarActivity<RecordTypeEditContract.Presenter>(),
        RecordTypeEditContract.View, IToolbarView.OnToolbarBackListener,
        View.OnClickListener, WBaseRVAdapter.OnItemClickListener<RecordTypeItem>,
        IToolbarView.OnToolbarTitleListener {

    private var ll: LinearLayout? = null
    private var fabAdd: FloatingActionButton? = null

    private var dlgTitle: AlertDialog? = null
    private var etTitle: EditText? = null

    private var rvSelectItem: RecyclerView? = null
    private var dlgSelectItem: BottomSheetDialog? = null

    private var mType: RecordType? = null

    override fun getContentView(): Int {
        return R.layout.activity_record_type_edit
    }

    override fun initData(bundle: Bundle?) {
        mType = try {
            bundle!!.getParcelable(RECORD_TYPE)
        } catch (ignored: Exception) {
            RecordType(-1, "", ArrayList(),
                    0, 0)
        }

        setPresenter(RecordTypeEditPresenter())
    }

    override fun initView() {
        super.initView()
        setToolbarBackListener(this)

        ll = findViewById(R.id.ll_record_type_edit)
        fabAdd = findViewById(R.id.fab_record_type_edit)
        fabAdd!!.setOnClickListener(this)
        setToolbarTitleListener(this)

        initDlg()
        initItems()
        initTitle()
    }

    private fun initDlg() {
        val view = LayoutInflater.from(this)
                .inflate(R.layout.dlg_edit, null)
        etTitle = view.findViewById(R.id.et_dlg_edit)
        etTitle!!.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                setTitle(etTitle!!.text.toString())
                dlgTitle!!.dismiss()
            }
            false
        }
        dlgTitle = AlertDialog.Builder(this)
                .setView(view)
                .setTitle("记录类型名称：")
                .setPositiveButton("确定") { dialog, which -> setTitle(etTitle!!.text.toString()) }
                .setNegativeButton("取消") { dialog, which -> finish() }
                .setCancelable(false)
                .create()

        rvSelectItem = LayoutInflater.from(this)
                .inflate(R.layout.dlg_select_type, null) as RecyclerView
        val adapter = SelectItemAdapter()
        adapter.data = mPresenter.selectItems
        adapter.setOnItemClickListener(this)
        rvSelectItem!!.adapter = adapter
        rvSelectItem!!.layoutManager = LinearLayoutManager(this)
        dlgSelectItem = BottomSheetDialog(this)
        dlgSelectItem!!.setContentView(rvSelectItem)
    }

    private fun initItems() {
        ll!!.removeAllViews()
        for (view in mType!!.views!!) {
            val v = mPresenter.generateView(this, view, ll!!)
            ll!!.addView(v)
            v!!.setOnClickListener { }
        }
    }

    private fun initTitle() {
        if (mType!!.title!!.isEmpty()) {
            dlgTitle!!.show()
        } else {
            setToolbarTitle(mType!!.title!!)
        }
    }

    private fun setTitle(title: String) {
        setToolbarTitle(title)
        mType!!.title = title
    }

    override fun showSelectDlg() {
        dlgSelectItem!!.show()
    }

    override fun onBackClick() {
        onBackPressed()
    }

    override fun onTitleClick() {
        etTitle!!.setText(mType!!.title)
        etTitle!!.setSelection(mType!!.title!!.length)
        dlgTitle!!.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_record_type_edit -> showSelectDlg()
        }
    }

    override fun onItemClick(recordTypeItem: RecordTypeItem) {
        when (recordTypeItem.type) {
            RecordTypeItem.TYPE_TEXT -> {
                val intent = Intent(this, TypeItemEditActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(TypeItemEditActivity.RECORD_TYPE, RecordTypeItem.TYPE_TEXT)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    companion object {
        val RECORD_TYPE = "type"
    }
}
