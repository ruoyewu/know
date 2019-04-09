package com.wuruoye.know.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.wuruoye.know.R
import com.wuruoye.know.model.beans.RecordType
import com.wuruoye.library.adapter.WBaseRVAdapter

class RecordTypeRVAdapter : WBaseRVAdapter<RecordType>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_select_type, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
//        val type = if (i < super.getItemCount()) getData(i) else RecordType("不限")
        val type = if (i == 0) RecordType("不限") else getData(i-1)
        val vh = viewHolder as ViewHolder
        vh.tv.text = type.title
        vh.tv.setOnClickListener { onItemClick(type) }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    private class ViewHolder internal constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        internal val tv: TextView = itemView.findViewById(R.id.tv_select_type)
    }
}
