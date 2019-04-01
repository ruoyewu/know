package com.wuruoye.know.ui.edit.contract

import android.content.Context
import com.wuruoye.know.model.beans.Record
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.library.contract.WIView
import com.wuruoye.library.contract.WPresenter

/**
 * Created at 2019/3/19 10:56 by wuruoye
 * Description:
 */
interface RecordEditContract {
    interface View : WIView {
        fun setViewInfo(recordView: RecordView, view: android.view.View, info: String)
        fun getViewInfo(recordView: RecordView, view: android.view.View): String
    }

    abstract class Presenter : WPresenter<View>() {
        abstract fun getRecordType(context: Context, id: Int): RecordType
        abstract fun getRecord(context: Context, id: Int): Record
        abstract fun generateRecord(context: Context, type: Int): Record
    }
}