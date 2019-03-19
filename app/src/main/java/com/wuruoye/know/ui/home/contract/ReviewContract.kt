package com.wuruoye.know.ui.home.contract

import com.wuruoye.library.contract.WIView
import com.wuruoye.library.contract.WPresenter

/**
 * Created at 2019/3/19 10:55 by wuruoye
 * Description:
 */
interface ReviewContract {
    interface View : WIView

    abstract class Presenter : WPresenter<View>()
}