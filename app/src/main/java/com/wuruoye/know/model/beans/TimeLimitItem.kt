package com.wuruoye.know.model.beans

/**
 * Created at 2019/4/1 20:24 by wuruoye
 * Description:
 */
class TimeLimitItem(
        var id: Int,
        var title: String
) {
    constructor(title: String): this(-1, title)
}