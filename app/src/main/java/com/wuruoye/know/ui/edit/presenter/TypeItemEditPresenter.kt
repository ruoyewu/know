package com.wuruoye.know.ui.edit.presenter

import com.wuruoye.know.model.beans.*
import com.wuruoye.know.ui.edit.contract.TypeItemEditContract
import com.wuruoye.know.ui.edit.controller.*

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
        } else if (view is RecordImageView || type == RecordTypeItem.TYPE_IMG) {
            ImageViewController(
                    if (view == null) RecordImageView()
                    else view as RecordImageView
            ) as EditorController
        }
        else {
            null
        }
    }
}
