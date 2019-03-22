package com.wuruoye.know.ui.home.presenter

import android.content.Context

import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.ui.home.contract.RecordContract
import com.wuruoye.know.util.sql.SqlUtil

class RecordPresenter : RecordContract.Presenter() {
    override fun getSelectType(context: Context): List<RecordType> {
        val types: List<RecordType> = SqlUtil.getInstance(context).getRecordTypeWithoutItems()
        val add = RecordType("点击增加更多类型")
        val result = ArrayList<RecordType>(types)
        result.add(add)
        return result
    }
}
