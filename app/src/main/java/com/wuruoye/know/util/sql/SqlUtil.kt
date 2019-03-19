package com.wuruoye.know.util.sql

import android.content.Context
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.know.util.sql.table.RecordTypeTable
import java.util.*

/**
 * Created at 2019/3/18 12:21 by wuruoye
 * Description:
 */
class SqlUtil private constructor(context: Context) {

    private val sh: SqliteHelper = SqliteHelper(context)

    val recordTypeWithoutItems: List<RecordType>
        get() {
            val result = ArrayList<RecordType>()
            val db = sh.readableDatabase
            try {
                val typeTables = RecordTypeTable.queryAll(db)

                for (table in typeTables) {
                    result.add(RecordType(table.id, table.title, arrayListOf(),
                            table.createTime, table.updateTime))
                }
            } finally {
                db.close()
            }
            return result
        }

    companion object {
        @Volatile
        private var sInstance: SqlUtil? = null

        fun getInstance(context: Context): SqlUtil? {
            if (sInstance == null) {
                synchronized(SqlUtil::class.java) {
                    if (sInstance == null) {
                        sInstance = SqlUtil(context)
                    }
                }
            }
            return sInstance
        }
    }
}
