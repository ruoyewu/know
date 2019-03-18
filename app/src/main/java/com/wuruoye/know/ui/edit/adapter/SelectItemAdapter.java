package com.wuruoye.know.ui.edit.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuruoye.know.R;
import com.wuruoye.know.model.beans.RecordTypeItem;
import com.wuruoye.library.adapter.WBaseRVAdapter;

/**
 * Created at 2019/3/18 08:29 by wuruoye
 * Description:
 */
public class SelectItemAdapter extends WBaseRVAdapter<RecordTypeItem> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_select_type, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final RecordTypeItem item = getData(i);
        ViewHolder vh = (ViewHolder) viewHolder;
        vh.tv.setText(item.getTitle());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(item);
            }
        });
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_select_type);
        }
    }
}
