package com.wuruoye.know.model.beans

/**
 * Created at 2019/4/1 10:34 by wuruoye
 * Description:
 */
class Record(
        var id: Int,
        var type: Int,
        var items: ArrayList<String>,
        var reviewNum: Int,
        var failNum: Int,
        var lastReview: Long,
        var createTime: Long,
        var updateTime: Long
){
    constructor(type: Int): this(-1, type, arrayListOf(), 0, 0,
            -1, -1, -1)
}