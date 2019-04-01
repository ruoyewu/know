package com.wuruoye.know.ui.edit.presenter

import android.content.Context
import com.wuruoye.know.model.beans.Record
import com.wuruoye.know.model.beans.RecordType
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
}