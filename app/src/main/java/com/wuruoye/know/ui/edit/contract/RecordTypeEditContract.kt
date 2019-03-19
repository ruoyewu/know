package com.wuruoye.know.ui.edit.contract

import android.content.Context
import android.view.ViewGroup

import com.wuruoye.know.model.beans.RecordTypeItem
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.library.contract.WIView
import com.wuruoye.library.contract.WPresenter

interface RecordTypeEditContract {
    interface View : WIView {
        fun showSelectDlg()
    }

    abstract class Presenter : WPresenter<View>() {
        abstract val selectItems: List<RecordTypeItem>
        abstract fun generateView(context: Context,
                                  view: RecordView, parent: ViewGroup): android.view.View?
    }
}
