package com.wuruoye.know.ui.home.contract

import android.content.Context

import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.library.contract.WIView
import com.wuruoye.library.contract.WPresenter

interface RecordContract {
    interface View : WIView {
        fun showSelectTypeDlg()
    }

    abstract class Presenter : WPresenter<View>() {
        abstract fun getSelectType(context: Context): List<RecordType>
    }
}
