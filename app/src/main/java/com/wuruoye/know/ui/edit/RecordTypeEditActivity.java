package com.wuruoye.know.ui.edit;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.wuruoye.know.R;
import com.wuruoye.know.base.IToolbarView;
import com.wuruoye.know.base.ToolbarActivity;
import com.wuruoye.know.ui.edit.contract.RecordTypeEditContract;
import com.wuruoye.know.ui.edit.presenter.RecordTypeEditPresenter;

public class RecordTypeEditActivity extends ToolbarActivity<RecordTypeEditContract.Presenter>
        implements RecordTypeEditContract.View, IToolbarView.ToolbarClickListener {
    private LinearLayout ll;
    private FloatingActionButton fabAdd;
    private RecyclerView rvSelectItem;
    private BottomSheetDialog dlgSelectItem;

    @Override
    protected int getContentView() {
        return R.layout.activity_record_type_edit;
    }

    @Override
    protected void initData(Bundle bundle) {
        setPresenter(new RecordTypeEditPresenter());
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle("编辑记录类型");
        setToolbarClickListener(this);

        ll = findViewById(R.id.ll_record_type_edit);
        fabAdd = findViewById(R.id.fab_record_type_edit);

        initDlg();
    }

    private void initDlg() {
        rvSelectItem = (RecyclerView) LayoutInflater.from(this)
                .inflate(R.layout.dlg_select_type, null);

    }

    @Override
    public void showSelectDlg() {
        dlgSelectItem.show();
    }

    @Override
    public void onBackClick() {
        onBackPressed();
    }
}
