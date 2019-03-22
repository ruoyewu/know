package com.wuruoye.know.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.wuruoye.know.R
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.ui.edit.RecordTypeEditActivity
import com.wuruoye.know.ui.home.adapter.SelectTypeRVAdapter
import com.wuruoye.know.ui.home.contract.RecordContract
import com.wuruoye.know.ui.home.presenter.RecordPresenter
import com.wuruoye.library.adapter.WBaseRVAdapter
import com.wuruoye.library.ui.WBaseFragment
import kotlinx.android.synthetic.main.fragment_record.*

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:24.
 * Description :
 */
class RecordFragment : WBaseFragment<RecordContract.Presenter>(),
        RecordContract.View, View.OnClickListener,
        WBaseRVAdapter.OnItemClickListener<RecordType>,
        WBaseRVAdapter.OnItemLongClickListener<RecordType> {
    private var dlgSelectType: BottomSheetDialog? = null
    private var rvSelectType: RecyclerView? = null


    override fun getContentView(): Int {
        return R.layout.fragment_record
    }

    override fun initData(bundle: Bundle?) {
        setPresenter(RecordPresenter())
    }

    override fun initView(view: View) {
        fab_record.setOnClickListener(this)

        initDlg()
    }

    @SuppressLint("InflateParams")
    private fun initDlg() {
        rvSelectType = LayoutInflater.from(context)
                .inflate(R.layout.dlg_select_type, null) as RecyclerView
        val adapter = SelectTypeRVAdapter()
        adapter.setOnItemClickListener(this)
        adapter.setOnItemLongClickListener(this)
        rvSelectType!!.adapter = adapter
        adapter.data = mPresenter.getSelectType(context!!)
        rvSelectType!!.layoutManager = LinearLayoutManager(context)
        dlgSelectType = BottomSheetDialog(context!!)
        dlgSelectType!!.setContentView(rvSelectType)
        dlgSelectType!!.setTitle("选择记录类型：")
    }

    override fun showSelectTypeDlg() {
        //        ((SelectTypeRVAdapter) rvSelectType.getAdapter()).setData(mPresenter.getSelectType());
        dlgSelectType!!.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_record -> showSelectTypeDlg()
        }
    }

    override fun onItemClick(recordType: RecordType) {
        if (recordType.id < 0) {
            // add
            val intent = Intent(context, RecordTypeEditActivity::class.java)
            startActivity(intent)
        } else {
            // normal
            Toast.makeText(context, recordType.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemLongClick(recordType: RecordType) {
        if (recordType.id >= 0) {
            val intent = Intent(context, RecordTypeEditActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(RecordTypeEditActivity.RECORD_TYPE, recordType)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        dlgSelectType!!.dismiss()
    }
}
