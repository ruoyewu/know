package com.wuruoye.know.model.beans

/**
 * Created at 2019/3/18 08:33 by wuruoye
 * Description:
 */
class RecordTypeItem(var type: Int, var title: String?) {
    companion object {
        const val TYPE_TEXT = 1
        const val TYPE_EDIT = 2
        const val TYPE_IMG = 3
    }
}
