package com.wuruoye.know.base

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.wuruoye.library.model.WConfig
import com.wuruoye.library.util.thread.DefaultThreadPool

/**
 * Created at 2019/3/18 19:48 by wuruoye
 * Description:
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        WConfig.init(this)
        //        WConfig.initNet(new OKHttpNet());
        WConfig.initThreadPool(DefaultThreadPool())
        WConfig.setLog(true)

        catchThrowable()
    }

    private fun catchThrowable() {
        Handler(Looper.getMainLooper())
                .post {
                    while (true) {
                        try {
                            Looper.loop()
                        } catch (t: Throwable) {
                            t.printStackTrace()
                        }
                    }
                }

        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            println("uncaught exception in thread " + t.name)
            e.printStackTrace()
        }
    }
}
