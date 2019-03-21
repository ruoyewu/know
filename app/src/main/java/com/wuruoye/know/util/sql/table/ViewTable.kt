package com.wuruoye.know.util.sql.table

/**
 * Created at 2019/3/17 21:18 by wuruoye
 * Description:
 */
abstract class ViewTable(id: Int) : Table(id) {
    companion object {
        val TEXT_VIEW = 1
    }
}
