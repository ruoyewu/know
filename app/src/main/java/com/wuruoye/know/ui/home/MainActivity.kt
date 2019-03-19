package com.wuruoye.know.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.wuruoye.know.R
import com.wuruoye.know.base.ToolbarActivity
import com.wuruoye.know.ui.home.contract.MainContract
import com.wuruoye.library.adapter.FragmentVPAdapter
import com.wuruoye.library.adapter.OnPageChangeListenerAdapter
import com.wuruoye.library.widget.changetab.ChangeTabLayout
import java.util.*

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:19.
 * Description :
 */
class MainActivity : ToolbarActivity<MainContract.Presenter>() {
    private var vp: ViewPager? = null
    private var ctl: ChangeTabLayout? = null

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initData(bundle: Bundle?) {

    }

    override fun initView() {
        super.initView()
        vp = findViewById(R.id.vp_main)
        ctl = findViewById(R.id.ctl_main)

        val adapter = FragmentVPAdapter(supportFragmentManager,
                Arrays.asList(*TITLE), Arrays.asList<Fragment>(ReviewFragment(),
                RecordFragment(), UserFragment()))
        vp!!.addOnPageChangeListener(object : OnPageChangeListenerAdapter() {
            override fun onPageSelected(position: Int) {
                setToolbarTitle(TITLE[position])
            }
        })
        vp!!.adapter = adapter
        ctl!!.attachViewPager(vp)
        vp!!.currentItem = 1
    }

    companion object {
        private val TITLE = arrayOf("复习", "记录", "用户")
    }
}
