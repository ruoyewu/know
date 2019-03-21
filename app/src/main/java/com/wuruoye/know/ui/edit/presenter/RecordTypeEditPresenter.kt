package com.wuruoye.know.ui.edit.presenter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.model.beans.RecordTypeItem
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.ui.edit.contract.RecordTypeEditContract
import com.wuruoye.know.util.sql.SqlUtil

class RecordTypeEditPresenter : RecordTypeEditContract.Presenter() {

    override val selectItems: List<RecordTypeItem>
        get() {
            val item = RecordTypeItem(RecordTypeItem.TYPE_TEXT, "文本")
            return listOf(item)
        }

    override fun generateView(context: Context, view: RecordView, parent: ViewGroup): View? {
        return ViewFactory.generateView(context, view, parent)
    }

    override fun saveRecordType(context: Context, recordType: RecordType) {
        SqlUtil.getInstance(context).saveRecordType(recordType)
    }
}
