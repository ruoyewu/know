package com.wuruoye.know.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wuruoye.know.R
import com.wuruoye.know.model.beans.TimeLimitItem
import com.wuruoye.library.adapter.WBaseRVAdapter

/**
 * Created at 2019/4/1 20:23 by wuruoye
 * Description:
 */
class TimeLimitRVAdapter : WBaseRVAdapter<TimeLimitItem>() {
    private var mListener: OnTimeLimitItemClick? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context)
                .inflate(R.layout.item_select_type, p0, false))
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val vh = p0 as ViewHolder
        val item = getData(p1)
        with(vh) {
            tv.text = item.title
            itemView.setOnClickListener {
                if (mListener != null) {
                    mListener!!.onTimeLimitItemClick(item)
                }
            }
        }
    }

    private class ViewHolder internal constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        internal val tv: TextView = itemView.findViewById(R.id.tv_select_type)
    }

    fun setOnTimeLimitItemClickListener(listener: OnTimeLimitItemClick) {
        mListener = listener
    }

    interface OnTimeLimitItemClick {
        fun onTimeLimitItemClick(item: TimeLimitItem)
    }
}