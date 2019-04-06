package com.wuruoye.know.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wuruoye.know.R

import com.wuruoye.know.model.beans.Record
import com.wuruoye.know.util.DateUtil
import com.wuruoye.know.util.sql.SqlUtil
import com.wuruoye.library.adapter.WBaseRVAdapter

/**
 * Created at 2019/4/5 15:04 by wuruoye
 * Description:
 */
class RecordRVAdapter : WBaseRVAdapter<Record>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_record_list, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val record = getData(i)
        val vh = viewHolder as ViewHolder
        with(vh) {
            val sql = SqlUtil.getInstance(itemView.context)
            val type = sql.queryRecordType(record.type)
            tvTitle.text = type.title
            // TODO 根据记录获得记录内容
            tvContent.text = sql.queryRecordItem(record.id,
                    SqlUtil.ViewTableItem.TEXT_VIEW)?.content
            tvDate.text = DateUtil.milli2Date(record.createTime)
            itemView.setOnClickListener {
                onItemClick(record)
            }
            itemView.setOnLongClickListener {
                onItemLongClick(record)
                true
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        internal val tvTitle = itemView.findViewById<TextView>(R.id.tv_title_record_list)
        internal val tvContent = itemView.findViewById<TextView>(R.id.tv_content_record_list)
        internal val tvDate = itemView.findViewById<TextView>(R.id.tv_date_record_list)
        internal val iv = itemView.findViewById<ImageView>(R.id.iv_record_list)
    }
}
