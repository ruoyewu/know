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
    private TextView tvTitle;
    private IToolbarView.ToolbarClickListener mListener;

    @Override
    protected void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        ivBack = toolbar.findViewById(R.id.iv_back_toolbar);
        tvBack = toolbar.findViewById(R.id.tv_back_toolbar);
        tvTitle = toolbar.findViewById(R.id.tv_title_toolbar);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onBackClick();
                }
            }
        });
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setToolbarClickListener(IToolbarView.ToolbarClickListener listener) {
        mListener = listener;
    }

    @Override
    public void setToolbarBack(int resource, String title) {
        ivBack.setImageResource(resource);
        tvBack.setText(title);
    }

    @Override
    public void setToolbarTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
    }
}
