package com.wuruoye.know.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import com.wuruoye.know.R
import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.ui.home.contract.MainContract
import com.wuruoye.library.adapter.FragmentVPAdapter
import com.wuruoye.library.adapter.OnPageChangeListenerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:19.
 * Description :
 */
class MainActivity : ToolbarActivity<MainContract.Presenter>() {

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initData(bundle: Bundle?) {

    }

    override fun initView() {
        super.initView()

        val adapter = FragmentVPAdapter(supportFragmentManager,
                Arrays.asList(*TITLE), Arrays.asList<Fragment>(ReviewFragment(),
                RecordFragment(), UserFragment()))
        vp_main.addOnPageChangeListener(object : OnPageChangeListenerAdapter() {
            override fun onPageSelected(position: Int) {
                setToolbarTitle(TITLE[position])
            }
        })
        vp_main.adapter = adapter
        ctl_main.attachViewPager(vp_main)
        vp_main.currentItem = 1
    }

    companion object {
        private val TITLE = arrayOf("复习", "记录", "用户")
    }
}
