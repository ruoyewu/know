package com.wuruoye.know.ui.edit.contract

import android.content.Context
import android.view.ViewGroup
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.RecordType

import com.wuruoye.know.model.beans.RecordTypeItem
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.library.contract.WIView
import com.wuruoye.library.contract.WPresenter

interface RecordTypeEditContract {
    interface View : WIView {
        fun showSelectDlg()
        fun onViewBack(view: RecordView, type: Int)
    }

    abstract class Presenter : WPresenter<View>() {
        abstract val selectItems: List<RecordTypeItem>
        abstract fun getRecordType(context: Context, id: Int): RecordType
        abstract fun getDefaultRecordType(): RecordType
        abstract fun generateView(context: Context, view: RecordView,
                                  parent: ViewGroup, attach: Boolean,
                                  listener: ViewFactory.OnLongClickListener): android.view.View?
        abstract fun saveRecordType(context: Context, recordType: RecordType)
    }
}
