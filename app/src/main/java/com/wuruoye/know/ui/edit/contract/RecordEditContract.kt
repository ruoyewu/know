package com.wuruoye.know.ui.edit.contract

import com.wuruoye.library.contract.WIView
import com.wuruoye.library.contract.WPresenter

/**
 * Created at 2019/3/19 10:56 by wuruoye
 * Description:
 */
interface RecordEditContract {
    interface View : WIView

    abstract class Presenter : WPresenter<View>()
}