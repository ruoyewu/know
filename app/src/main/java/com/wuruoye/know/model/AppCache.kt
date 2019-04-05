package com.wuruoye.know.model

import com.wuruoye.library.model.WBaseCache

/**
 * Created at 2019/4/1 16:34 by wuruoye
 * Description:
 */
class AppCache private constructor() : WBaseCache("com.wuruoye.know") {

    var recordType: Int
        get() = getInt(RECORD_TYPE, -1)
        set(value) = putInt(RECORD_TYPE, value)

    var timeLimit: Int
        get() = getInt(TIME_LIMIT, 0)
        set(value) = putInt(TIME_LIMIT, value)

    companion object {
        @Volatile
        private var sInstance: AppCache? = null

        fun getInstance(): AppCache {
            if (sInstance == null) {
                synchronized(this) {
                    if (sInstance == null) {
                        sInstance = AppCache()
                    }
                }
            }
            return sInstance!!
        }

        const val RECORD_TYPE = "record_type"
        const val TIME_LIMIT = "time_limit"
    }
}