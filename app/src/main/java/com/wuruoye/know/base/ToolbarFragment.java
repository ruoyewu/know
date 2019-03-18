package com.wuruoye.know.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuruoye.know.R;
import com.wuruoye.library.contract.WIPresenter;
import com.wuruoye.library.ui.WBaseFragment;

public abstract class ToolbarFragment<T extends WIPresenter> extends WBaseFragment<T>
        implements IToolbarView{
    private Toolbar toolbar;
    private ImageView ivBack;
    private TextView tvBack;
    private ImageView ivMore;
    private TextView tvMore;
    private TextView tvTitle;

    @Override
    protected void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        ivBack = toolbar.findViewById(R.id.iv_back_toolbar);
        tvBack = toolbar.findViewById(R.id.tv_back_toolbar);
        ivMore = toolbar.findViewById(R.id.iv_more_toolbar);
        tvMore = toolbar.findViewById(R.id.tv_more_toolbar);
        tvTitle = toolbar.findViewById(R.id.tv_title_toolbar);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setToolbarBackListener(final OnToolbarBackListener listener) {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBackClick();
            }
        });
    }

    @Override
    public void setToolbarMoreListener(final OnToolbarMoreListener listener) {
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMoreClick();
            }
        });
    }

    @Override
    public void setToolbarBack(int resource, String title) {
        ivBack.setImageResource(resource);
        tvBack.setText(title);
    }

    @Override
    public void setToolbarMore(int resource, String title) {
        ivMore.setImageResource(resource);
        tvMore.setText(title);
    }

    @Override
    public void setToolbarTitle(String title) {
        tvTitle.setText(title);
    }
}
