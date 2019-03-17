package com.wuruoye.know.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.wuruoye.know.R;
import com.wuruoye.know.ui.edit.RecordTypeEditActivity;
import com.wuruoye.know.ui.home.adapter.SelectTypeRVAdapter;
import com.wuruoye.know.ui.home.contract.RecordContract;
import com.wuruoye.know.ui.home.presenter.RecordPresenter;
import com.wuruoye.know.util.sql.table.RecordTypeTable;
import com.wuruoye.library.adapter.WBaseRVAdapter;
import com.wuruoye.library.ui.WBaseFragment;

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:24.
 * Description :
 */
public class RecordFragment extends WBaseFragment<RecordContract.Presenter>
        implements RecordContract.View, View.OnClickListener,
        WBaseRVAdapter.OnItemClickListener<RecordTypeTable> {
    private BottomSheetDialog dlgSelectType;
    private RecyclerView rvSelectType;

    private FloatingActionButton fab;

    @Override
    protected int getContentView() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initData(Bundle bundle) {
        setPresenter(new RecordPresenter());
    }

    @Override
    protected void initView(View view) {
        fab = view.findViewById(R.id.fab_record);
        fab.setOnClickListener(this);

        initDlg();
    }

    private void initDlg() {
        rvSelectType = (RecyclerView) LayoutInflater.from(getContext())
                .inflate(R.layout.dlg_select_type, null);
        SelectTypeRVAdapter adapter = new SelectTypeRVAdapter();
        adapter.setOnItemClickListener(this);
        rvSelectType.setAdapter(adapter);
        adapter.setData(mPresenter.getSelectType());
        rvSelectType.setLayoutManager(new LinearLayoutManager(getContext()));
        dlgSelectType = new BottomSheetDialog(getContext());
        dlgSelectType.setContentView(rvSelectType);
        dlgSelectType.setTitle("选择记录类型：");
    }

    @Override
    public void showSelectTypeDlg() {
//        ((SelectTypeRVAdapter) rvSelectType.getAdapter()).setData(mPresenter.getSelectType());
        dlgSelectType.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_record:
                showSelectTypeDlg();
                break;
        }
    }

    @Override
    public void onItemClick(RecordTypeTable recordTypeTable) {
        if (recordTypeTable.getId() < 0) {
            // add
            Intent intent = new Intent(getContext(), RecordTypeEditActivity.class);
            startActivity(intent);
        } else {
            // normal
            Toast.makeText(getContext(), recordTypeTable.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
