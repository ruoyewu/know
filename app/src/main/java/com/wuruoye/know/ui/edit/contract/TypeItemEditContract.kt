package com.wuruoye.know.ui.edit.contract

import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.ui.edit.controller.EditorController
import com.wuruoye.library.contract.WIView
import com.wuruoye.library.contract.WPresenter

/**
 * Created at 2019/3/18 15:32 by wuruoye
 * Description:
 */
interface TypeItemEditContract {
    interface View : WIView

    abstract class Presenter : WPresenter<View>() {
        abstract fun generateController(type: Int, view: RecordView?): EditorController?
    }
}
