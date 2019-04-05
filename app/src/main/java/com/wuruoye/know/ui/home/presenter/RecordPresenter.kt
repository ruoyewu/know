package com.wuruoye.know.ui.home.presenter

import android.content.Context
import com.wuruoye.know.model.AppCache
import com.wuruoye.know.model.beans.Record

import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.ui.home.contract.RecordContract
import com.wuruoye.know.util.sql.SqlUtil

class RecordPresenter : RecordContract.Presenter() {
    private val mAppCache = AppCache.getInstance()

    override fun getRecordType(context: Context): List<RecordType> {
        val types: List<RecordType> = SqlUtil.getInstance(context).queryRecordTypeWithoutItems()
        val result = ArrayList<RecordType>(types)
        return result
    }

    override fun getRecord(context: Context, limitType: Int,
                           recordType: Int): List<Record> {
        return SqlUtil.getInstance(context).queryRecordWithTypeTime(recordType,
                System.currentTimeMillis() - getTimeLimit(limitType))
    }

    override fun getRecordType(): Int {
        return mAppCache.recordType
    }

    override fun getRecordTypeTitle(context: Context, id: Int): String {
        return if (id >= 0) SqlUtil.getInstance(context).queryRecordType(id).title
                else "不限"
    }

    override fun getTimeLimit(): Int {
        return mAppCache.timeLimit
    }

    override fun setRecordType(type: Int) {
        mAppCache.recordType = type
    }

    override fun setTimeLimit(type: Int) {
        mAppCache.timeLimit = type
    }

    override fun deleteRecord(context: Context, id: Int): Boolean {
        return SqlUtil.getInstance(context)
                .deleteRecord(id)
    }

    private fun getTimeLimit(limitType: Int): Long {
        val oneDay = 86400000L
        return when (limitType) {
            1 -> oneDay
            2 -> oneDay * 2
            3 -> oneDay * 3
            4 -> oneDay * 7
            5 -> oneDay * 30
            6 -> oneDay * 365
            else -> Long.MAX_VALUE
        }
    }
}
