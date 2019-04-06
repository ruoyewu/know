package com.wuruoye.know.ui.edit.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.wuruoye.know.R
import com.wuruoye.know.model.beans.RecordTypeItem
import com.wuruoye.library.adapter.WBaseRVAdapter

/**
 * Created at 2019/3/18 08:29 by wuruoye
 * Description:
 */
class SelectItemAdapter : WBaseRVAdapter<RecordTypeItem>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_select_type, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val item = getData(i)
        val vh = viewHolder as ViewHolder
        vh.tv.text = item.title
        vh.itemView.setOnClickListener { onItemClick(item) }
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tv: TextView = itemView.findViewById(R.id.tv_select_type)
    }
}
