package com.wuruoye.know.ui.home.contract

import android.content.Context
import com.wuruoye.know.model.beans.Record

import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.library.contract.WIView
import com.wuruoye.library.contract.WPresenter

interface RecordContract {
    interface View : WIView {
        fun showSelectTypeDlg()
        fun showTimeLimitDlg()
        fun showRecordTypeDlg()
        fun refreshRecordList()
    }

    abstract class Presenter : WPresenter<View>() {
        abstract fun getRecordType(context: Context): List<RecordType>
        abstract fun getRecord(context: Context, limitType: Int,
                               recordType: Int): List<Record>
        abstract fun getRecordType(): Int
        abstract fun getRecordTypeTitle(context: Context, id: Int): String
        abstract fun getTimeLimit(): Int
        abstract fun setRecordType(type: Int)
        abstract fun setTimeLimit(type: Int)
        abstract fun deleteRecord(context: Context, id: Int): Boolean
    }
}
