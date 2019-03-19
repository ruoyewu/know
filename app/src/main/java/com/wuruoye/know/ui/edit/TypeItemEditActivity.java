package com.wuruoye.know.ui.edit;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.wuruoye.know.R;
import com.wuruoye.know.base.ToolbarActivity;
import com.wuruoye.know.model.beans.RecordView;
import com.wuruoye.know.ui.edit.contract.TypeItemEditContract;
import com.wuruoye.know.ui.edit.controller.EditorController;
import com.wuruoye.know.ui.edit.presenter.TypeItemEditPresenter;

public class TypeItemEditActivity extends ToolbarActivity<TypeItemEditContract.Presenter>
        implements TypeItemEditContract.View {
    public static final String RECORD_TYPE = "type";
    public static final String RECORD_VIEW = "view";

    private RecordView mView;
    private int mType;
    private EditorController mController;

    private FrameLayout flContent;
    private ScrollView svOptions;

    @Override
    protected int getContentView() {
        return R.layout.activity_type_item_edit;
    }

    @Override
    protected void initData(Bundle bundle) {
        try {
            mView = bundle.getParcelable(RECORD_VIEW);
        } catch (Exception ignore) {
            mType = bundle.getParcelable(RECORD_TYPE);
        }

        setPresenter(new TypeItemEditPresenter());
    }

    @Override
    protected void initView() {
        super.initView();

        mController = mPresenter.generateController(mType, mView);
        flContent = findViewById(R.id.fl_type_item_edit);
        svOptions = findViewById(R.id.sv_type_item_edit);
        mController.attach(this, flContent, svOptions);
    }
}
