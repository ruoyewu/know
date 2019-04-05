package com.wuruoye.know.ui.home

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.wuruoye.know.R
import com.wuruoye.know.model.beans.Record
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.model.beans.TimeLimitItem
import com.wuruoye.know.ui.edit.RecordEditActivity
import com.wuruoye.know.ui.edit.RecordTypeEditActivity
import com.wuruoye.know.ui.home.adapter.RecordRVAdapter
import com.wuruoye.know.ui.home.adapter.RecordTypeRVAdapter
import com.wuruoye.know.ui.home.adapter.RecordTypeSelectRVAdapter
import com.wuruoye.know.ui.home.adapter.TimeLimitRVAdapter
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
        WBaseRVAdapter.OnItemLongClickListener<RecordType>,
        TimeLimitRVAdapter.OnTimeLimitItemClick {

    private lateinit var dlgSelectType: BottomSheetDialog
    private lateinit var rvRecordTypeSelect: RecyclerView

    private lateinit var dlgTimeLimit: BottomSheetDialog

    private lateinit var dlgRecordType: BottomSheetDialog
    private lateinit var rvRecordType: RecyclerView

    private lateinit var tvRecordType: TextView
    private lateinit var tvCreateTime: TextView
    private lateinit var rvRecord: RecyclerView

    private var mCurLimitType = 0
    private var mCurRecordType = 0
    private var mCurType = TYPE_LIMIT

    override fun getContentView(): Int {
        return R.layout.fragment_record
    }

    override fun initData(bundle: Bundle?) {
        setPresenter(RecordPresenter())

        mCurRecordType = mPresenter.getRecordType()
        mCurLimitType = mPresenter.getTimeLimit()
    }

    override fun initView(view: View) {
        fab_record.setOnClickListener(this)

        tvRecordType = tv_record_type_record
        tvCreateTime = tv_create_time_record
        rvRecord = rv_record

        tvCreateTime.text = ITEM_TIME_LIMIT[mCurLimitType].title
        tvRecordType.text = mPresenter.getRecordTypeTitle(context!!, mCurRecordType)
        tvCreateTime.setOnClickListener(this)
        tvRecordType.setOnClickListener(this)

        initDlg()
        initRV()
    }

    @SuppressLint("InflateParams")
    private fun initDlg() {
        val recordTypeList = mPresenter.getRecordType(context!!)

        // 类型选择弹框
        rvRecordTypeSelect = LayoutInflater.from(context)
                .inflate(R.layout.dlg_record_type, null) as RecyclerView
        rvRecordTypeSelect.layoutManager = LinearLayoutManager(context)
        val adapterSelect = RecordTypeSelectRVAdapter()
        adapterSelect.data = recordTypeList
        adapterSelect.setOnItemClickListener(this)
        adapterSelect.setOnItemLongClickListener(this)
        rvRecordTypeSelect.adapter = adapterSelect
        dlgSelectType = BottomSheetDialog(context!!)
        dlgSelectType.setContentView(rvRecordTypeSelect)
        dlgSelectType.setTitle("选择记录类型：")


        // 类型选择弹框
        rvRecordType = LayoutInflater.from(context)
                .inflate(R.layout.dlg_record_type, null) as RecyclerView
        rvRecordType.layoutManager = LinearLayoutManager(context)
        val adapter = RecordTypeRVAdapter()
        adapter.setOnItemClickListener(this)
        adapter.data = recordTypeList
        rvRecordType.adapter = adapter
        dlgRecordType = BottomSheetDialog(context!!)
        dlgRecordType.setContentView(rvRecordType)
        dlgRecordType.setTitle("选择记录类型：")


        // 时间选择弹框
        val rvTime = LayoutInflater.from(context)
                .inflate(R.layout.dlg_record_type, null) as RecyclerView
        val timeAdapter = TimeLimitRVAdapter()
        timeAdapter.data = ITEM_TIME_LIMIT
        timeAdapter.setOnTimeLimitItemClickListener(this)
        rvTime.layoutManager = LinearLayoutManager(context)
        rvTime.adapter = timeAdapter
        dlgTimeLimit = BottomSheetDialog(context!!)
        dlgTimeLimit.setContentView(rvTime)
        dlgTimeLimit.setTitle("选择时间：")
    }

    private fun initRV() {
        val adapter = RecordRVAdapter()
        adapter.setOnItemClickListener {
            onRecordItemClick(it)
        }
        adapter.setOnItemLongClickListener {
            onRecordItemLongClick(it)
        }
        rvRecord.layoutManager = LinearLayoutManager(context)
        rvRecord.adapter = adapter
        refreshRecordList()
    }

    override fun showSelectTypeDlg() {
        mCurType = TYPE_SELECT
        dlgSelectType.show()
    }

    override fun showRecordTypeDlg() {
        mCurType = TYPE_LIMIT
        dlgRecordType.show()
    }

    override fun showTimeLimitDlg() {
        dlgTimeLimit.show()
    }

    override fun refreshRecordList() {
        val recordList = mPresenter.getRecord(context!!, mCurLimitType, mCurRecordType)
        val adapter = rvRecord.adapter as WBaseRVAdapter<*>
        adapter.data = recordList
    }

    private fun onRecordItemClick(record: Record) {
        Toast.makeText(context, record.items.find { !it.isEmpty() }, Toast.LENGTH_SHORT).show()
    }

    private fun onRecordItemLongClick(record: Record) {
        if (mPresenter.deleteRecord(context!!, record.id)) {
            (rvRecord.adapter as WBaseRVAdapter<Record>).removeData(record)
        } else {

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_record -> showSelectTypeDlg()
            R.id.tv_create_time_record -> showTimeLimitDlg()
            R.id.tv_record_type_record -> showRecordTypeDlg()
        }
    }

    override fun onTimeLimitItemClick(item: TimeLimitItem) {
        dlgTimeLimit.dismiss()
        mCurLimitType = item.id
        tvCreateTime.text = item.title
        mPresenter.setTimeLimit(mCurLimitType)
        refreshRecordList()
    }

    override fun onItemClick(recordType: RecordType) {
        if (mCurType == TYPE_LIMIT) {
            dlgRecordType.dismiss()
            mCurRecordType = recordType.id
            tvRecordType.text = recordType.title
            mPresenter.setRecordType(mCurRecordType)
            refreshRecordList()
        } else {
            dlgSelectType.dismiss()
            if (recordType.id < 0) {
                // add
                startActivityForResult(Intent(context, RecordTypeEditActivity::class.java),
                        FOR_ADD_RESULT)
            } else {
                // normal
                val intent = Intent(context, RecordEditActivity::class.java)
                intent.putExtra(RecordEditActivity.RECORD_TYPE, recordType.id)
                startActivityForResult(intent, FOR_ADD_RECORD_RESULT)
            }
        }
    }

    override fun onItemLongClick(recordType: RecordType) {
        dlgSelectType.dismiss()
        val intent = Intent(context, RecordTypeEditActivity::class.java)
        intent.putExtra(RecordTypeEditActivity.RECORD_TYPE, recordType)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FOR_ADD_RESULT && resultCode == RESULT_OK) {
            val recordTypeList = mPresenter.getRecordType(context!!)
            (rvRecordType.adapter as RecordTypeRVAdapter).data = recordTypeList
            (rvRecordTypeSelect.adapter as RecordTypeRVAdapter).data = recordTypeList
        } else if (requestCode == FOR_ADD_RECORD_RESULT && resultCode == RESULT_OK) {
            refreshRecordList()
        }
    }

    companion object {
        const val TYPE_SELECT = 1
        const val TYPE_LIMIT = 2
        const val FOR_ADD_RESULT = 1
        const val FOR_ADD_RECORD_RESULT = 2

        val ITEM_TIME_LIMIT = arrayListOf<TimeLimitItem>(
                TimeLimitItem(1, "一天内"),
                TimeLimitItem(2, "两天内"),
                TimeLimitItem(3, "三天内"),
                TimeLimitItem(4, "一周内"),
                TimeLimitItem(5, "一月内"),
                TimeLimitItem(6, "一年内"),
                TimeLimitItem(0, "不限"))
    }
}
