package com.wuruoye.know.ui.edit.presenter

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.view.ViewGroup
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.*
import com.wuruoye.know.ui.edit.contract.RecordEditContract
import com.wuruoye.know.util.sql.SqlUtil

/**
 * Created at 2019/4/1 10:04 by wuruoye
 * Description:
 */
class RecordEditPresenter : RecordEditContract.Presenter() {

    override fun getRecordType(context: Context, id: Int): RecordType {
        return SqlUtil.getInstance(context).queryRecordType(id)
    }

    override fun getRecord(context: Context, id: Int): Record {
        return SqlUtil.getInstance(context).queryRecord(id)
    }

    override fun generateRecord(context: Context, type: Int): Record {
        return Record(type)
    }

    override fun loadRecord(context: Context, record: Record,
                            recordType: RecordType, view: ViewGroup) {
        val recordId = record.id
        for (v in recordType.views) {
            ViewFactory.generateView(context, v, view, true)
        }
        if (recordId >= 0) {
            loadViews(context, recordType.views, recordId, view)
        }
    }

    override fun saveRecord(context: Context, record: Record,
                            recordType: RecordType, view: ViewGroup): Boolean {
        if (record.createTime > 0) {
            record.updateTime = System.currentTimeMillis()
        } else {
            record.createTime = System.currentTimeMillis()
        }
        return SqlUtil.getInstance(context).saveRecordWithItems(record, recordType, view)
    }

    private fun loadViews(context: Context, views: ArrayList<RecordView>,
                          recordId: Int, parent: ViewGroup) {
        for (i in 0 until views.size) {
            val v = views[i]
            val child = parent.getChildAt(i)
            if (v is RecordTextView && v.isEditable) {
                val item = SqlUtil.getInstance(context).queryRecordItem(recordId,
                        getType(v), v.id)
                if (item != null) {
                    val et = (child as TextInputLayout).editText!!
                    et.setText(item.content)
                    et.setSelection(item.content.length)
                    child.tag = item
                }
            } else if (v is RecordLayoutView) {
                loadViews(context, v.views, recordId, child as ViewGroup)
            }
        }
    }

    private fun getType(recordView: RecordView): Int {
        return when(recordView) {
            is RecordTextView -> SqlUtil.ViewTableItem.TEXT_VIEW
            is RecordLayoutView -> SqlUtil.ViewTableItem.LAYOUT_VIEW
            else -> -1
        }
    }
}