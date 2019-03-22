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
        val type = getData(i)
        val vh = viewHolder as ViewHolder
        vh.tv.text = type.title
        vh.tv.setOnClickListener { onItemClick(type) }
        if (type.id >= 0) {
            vh.tv.setOnLongClickListener { onItemLongClick(type); true}
        }
    }

    private class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tv: TextView = itemView.findViewById(R.id.tv_select_type)
    }
}
