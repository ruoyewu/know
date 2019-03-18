package com.wuruoye.know.ui.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuruoye.know.R;
import com.wuruoye.know.model.beans.RecordType;
import com.wuruoye.library.adapter.WBaseRVAdapter;

public class SelectTypeRVAdapter extends WBaseRVAdapter<RecordType> {
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_ADD = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_select_type, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final RecordType type;
        if (getItemViewType(i) == TYPE_NORMAL) {
            type = getData(i);
        } else {
            type = new RecordType(-1, "点击增加更多类型",
                    null, 0, 0);
        }
        ViewHolder vh = (ViewHolder) viewHolder;
        vh.tv.setText(type.getTitle());
        vh.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(type);
            }
        });
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_select_type);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < super.getItemCount()) {
            return TYPE_NORMAL;
        } else {
            return TYPE_ADD;
        }
    }
}
