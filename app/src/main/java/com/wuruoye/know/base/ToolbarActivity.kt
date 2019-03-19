package com.wuruoye.know.base

import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.wuruoye.know.R
import com.wuruoye.library.contract.WIPresenter
import com.wuruoye.library.ui.WBaseActivity

abstract class ToolbarActivity<T : WIPresenter> : WBaseActivity<T>(), IToolbarView {
    private lateinit var toolbar: Toolbar

    private var ivBack: ImageView? = null
    private var tvBack: TextView? = null
    private var ivMore: ImageView? = null
    private var tvMore: TextView? = null
    private var tvTitle: TextView? = null

    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        ivBack = toolbar.findViewById(R.id.iv_back_toolbar)
        tvBack = toolbar.findViewById(R.id.tv_back_toolbar)
        ivMore = toolbar.findViewById(R.id.iv_more_toolbar)
        tvMore = toolbar.findViewById(R.id.tv_more_toolbar)
        tvTitle = toolbar.findViewById(R.id.tv_title_toolbar)

        setSupportActionBar(toolbar)
    }

    override fun setToolbarBackListener(listener: IToolbarView.OnToolbarBackListener) {
        ivBack!!.setOnClickListener { listener.onBackClick() }
    }

    override fun setToolbarMoreListener(listener: IToolbarView.OnToolbarMoreListener) {
        ivMore!!.setOnClickListener { listener.onMoreClick() }
    }

    override fun setToolbarTitleListener(listener: IToolbarView.OnToolbarTitleListener) {
        tvTitle!!.setOnClickListener { listener.onTitleClick() }
    }

    override fun setToolbarBack(resource: Int, title: String) {
        ivBack!!.setImageResource(resource)
        tvBack!!.text = title
    }

    override fun setToolbarMore(resource: Int, title: String) {
        ivMore!!.setImageResource(resource)
        tvMore!!.text = title
    }

    override fun setToolbarTitle(title: String) {
        tvTitle!!.text = title
    }

    override fun getToolbar(): Toolbar {
        return toolbar
    }
}
