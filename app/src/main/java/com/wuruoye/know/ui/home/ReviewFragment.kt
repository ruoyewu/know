package com.wuruoye.know.ui.home

import android.os.Bundle
import android.view.View

import com.wuruoye.know.R
import com.wuruoye.know.ui.home.contract.ReviewContract
import com.wuruoye.library.ui.WBaseFragment

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:24.
 * Description :
 */
class ReviewFragment : WBaseFragment<ReviewContract.Presenter>() {
    override fun getContentView(): Int {
        return R.layout.fragment_review
    }

    override fun initData(bundle: Bundle?) {

    }

    override fun initView(view: View) {

    }
}
