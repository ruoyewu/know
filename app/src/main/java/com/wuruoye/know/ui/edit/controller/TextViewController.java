package com.wuruoye.know.ui.edit.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.wuruoye.know.R;
import com.wuruoye.know.model.ViewFactory;
import com.wuruoye.know.model.beans.RecordTextView;
import com.wuruoye.know.model.beans.RecordView;

/**
 * Created at 2019/3/18 16:52 by wuruoye
 * Description:
 */
public class TextViewController extends AbstractEditorController {
    private RecordTextView mView;
    private FrameLayout flContent;
    private ScrollView svOptions;

    private TextView mShowView;
    private TextView tvText;
    private TextView tvTextSize;
    private TextView tvTextColor;
    private TextView tvHint;
    private TextView tvHintSize;
    private TextView tvHintColor;
    private TextView tvMargin;
    private TextView tvPadding;
    private TextView tvTextStyle;
    private TextView tvInputType;
    private Switch sEditable;

    public TextViewController(RecordView mView) {
        this.mView = (RecordTextView) mView;
    }

    @Override
    public void attach(Context context, FrameLayout fl, ScrollView sv) {
        super.attach(context);
        flContent = fl;
        svOptions = sv;

        initView();
    }

    private void initView() {
        mShowView = (TextView) ViewFactory.generateView(mContext, mView, flContent);
        LayoutInflater.from(mContext)
                .inflate(R.layout.layout_text_view, svOptions);

        dlgMargin.show();
    }

    @Override
    public RecordView getResult() {
        return mView;
    }
}
