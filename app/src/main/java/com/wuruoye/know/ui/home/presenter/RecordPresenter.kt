package com.wuruoye.know.ui.home.presenter

import android.content.Context

import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.ui.home.contract.RecordContract
import com.wuruoye.know.util.sql.SqlUtil

class RecordPresenter : RecordContract.Presenter() {
    override fun getSelectType(context: Context): List<RecordType> {
        return SqlUtil.getInstance(context).getRecordTypeWithoutItems()
    }
}
