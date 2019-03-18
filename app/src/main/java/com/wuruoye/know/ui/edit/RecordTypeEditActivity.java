package com.wuruoye.know.ui.edit;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wuruoye.know.R;
import com.wuruoye.know.base.IToolbarView;
import com.wuruoye.know.base.ToolbarActivity;
import com.wuruoye.know.ui.edit.adapter.SelectItemAdapter;
import com.wuruoye.know.ui.edit.contract.RecordTypeEditContract;
import com.wuruoye.know.ui.edit.presenter.RecordTypeEditPresenter;
import com.wuruoye.know.model.beans.RecordTypeItem;
import com.wuruoye.know.util.sql.table.ViewTable;
import com.wuruoye.library.adapter.WBaseRVAdapter;

import java.util.List;

public class RecordTypeEditActivity extends ToolbarActivity<RecordTypeEditContract.Presenter>
        implements RecordTypeEditContract.View, IToolbarView.OnToolbarBackListener,
        View.OnClickListener, WBaseRVAdapter.OnItemClickListener<RecordTypeItem> {
    private LinearLayout ll;
    private FloatingActionButton fabAdd;
    private RecyclerView rvSelectItem;
    private BottomSheetDialog dlgSelectItem;

    private List<ViewTable> mViews;

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
        setToolbarBackListener(this);

        ll = findViewById(R.id.ll_record_type_edit);
        fabAdd = findViewById(R.id.fab_record_type_edit);
        fabAdd.setOnClickListener(this);

        initDlg();
    }

    private void initDlg() {
        rvSelectItem = (RecyclerView) LayoutInflater.from(this)
                .inflate(R.layout.dlg_select_type, null);
        SelectItemAdapter adapter = new SelectItemAdapter();
        adapter.setData(mPresenter.getSelectItems());
        adapter.setOnItemClickListener(this);
        rvSelectItem.setAdapter(adapter);
        rvSelectItem.setLayoutManager(new LinearLayoutManager(this));
        dlgSelectItem = new BottomSheetDialog(this);
        dlgSelectItem.setContentView(rvSelectItem);
    }

    @Override
    public void showSelectDlg() {
        dlgSelectItem.show();
    }

    @Override
    public void onBackClick() {
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_record_type_edit:
                showSelectDlg();
                break;
        }
    }

    @Override
    public void onItemClick(RecordTypeItem recordTypeItem) {

    }
}
