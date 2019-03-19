package com.wuruoye.know.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.wuruoye.know.R
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.library.adapter.WBaseRVAdapter

class SelectTypeRVAdapter : WBaseRVAdapter<RecordType>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_select_type, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val type: RecordType
        if (getItemViewType(i) == TYPE_NORMAL) {
            type = getData(i)
        } else {
            type = RecordType(-1, "点击增加更多类型",
                    arrayListOf(), 0, 0)
        }
        val vh = viewHolder as ViewHolder
        vh.tv.text = type.title
        vh.tv.setOnClickListener { onItemClick(type) }
    }

    private class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tv: TextView

        init {
            tv = itemView.findViewById(R.id.tv_select_type)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) {
            TYPE_NORMAL
        } else {
            TYPE_ADD
        }
    }

    companion object {
        val TYPE_NORMAL = 1
        val TYPE_ADD = 2
    }
}
