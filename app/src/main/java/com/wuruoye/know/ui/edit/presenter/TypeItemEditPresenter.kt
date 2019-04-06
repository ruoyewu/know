package com.wuruoye.know.ui.edit.presenter

import com.wuruoye.know.model.beans.RecordLayoutView
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordTypeItem
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.know.ui.edit.contract.TypeItemEditContract
import com.wuruoye.know.ui.edit.controller.EditTextController
import com.wuruoye.know.ui.edit.controller.EditorController
import com.wuruoye.know.ui.edit.controller.LayoutViewController
import com.wuruoye.know.ui.edit.controller.TextViewController

/**
 * Created at 2019/3/18 16:05 by wuruoye
 * Description:
 */
class TypeItemEditPresenter : TypeItemEditContract.Presenter() {

    override fun generateController(type: Int, view: RecordView?): EditorController? {
        return if ((view is RecordTextView && view.isEditable) || type == RecordTypeItem.TYPE_EDIT) {
            EditTextController(
                    if (view == null) RecordTextView(true)
                    else view as RecordTextView
            )
        } else if ((view is RecordTextView && !view.isEditable) || type == RecordTypeItem.TYPE_TEXT) {
            TextViewController(
                    if (view == null) RecordTextView()
                    else view as RecordTextView
            )
        } else if (view is RecordLayoutView || type == RecordTypeItem.TYPE_LAYOUT) {
            LayoutViewController(
                    if (view == null) RecordLayoutView()
                    else view as RecordLayoutView
            )
        }
        else {
            null
        }
    }
}
