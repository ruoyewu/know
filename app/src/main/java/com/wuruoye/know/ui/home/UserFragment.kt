package com.wuruoye.know.ui.home

import android.os.Bundle
import android.view.View

import com.wuruoye.know.R
import com.wuruoye.know.ui.home.contract.UserContract
import com.wuruoye.library.ui.WBaseFragment

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:25.
 * Description :
 */
class UserFragment : WBaseFragment<UserContract.Presenter>() {
    override fun getContentView(): Int {
        return R.layout.fragment_user
    }

    override fun initData(bundle: Bundle?) {

    }

    override fun initView(view: View) {

    }
}
