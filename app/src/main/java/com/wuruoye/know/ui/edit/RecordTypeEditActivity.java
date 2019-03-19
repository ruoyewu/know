package com.wuruoye.know.ui.edit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuruoye.know.R;
import com.wuruoye.know.base.IToolbarView;
import com.wuruoye.know.base.ToolbarActivity;
import com.wuruoye.know.model.beans.RecordType;
import com.wuruoye.know.model.beans.RecordTypeItem;
import com.wuruoye.know.model.beans.RecordView;
import com.wuruoye.know.ui.edit.adapter.SelectItemAdapter;
import com.wuruoye.know.ui.edit.contract.RecordTypeEditContract;
import com.wuruoye.know.ui.edit.presenter.RecordTypeEditPresenter;
import com.wuruoye.library.adapter.WBaseRVAdapter;

import java.util.ArrayList;

public class RecordTypeEditActivity extends ToolbarActivity<RecordTypeEditContract.Presenter>
        implements RecordTypeEditContract.View, IToolbarView.OnToolbarBackListener,
        View.OnClickListener, WBaseRVAdapter.OnItemClickListener<RecordTypeItem>, IToolbarView.OnToolbarTitleListener {
    public static final String RECORD_TYPE = "type";

    private LinearLayout ll;
    private FloatingActionButton fabAdd;

    private AlertDialog dlgTitle;
    private EditText etTitle;

    private RecyclerView rvSelectItem;
    private BottomSheetDialog dlgSelectItem;

    private RecordType mType;

    @Override
    protected int getContentView() {
        return R.layout.activity_record_type_edit;
    }

    @Override
    protected void initData(Bundle bundle) {
        try {
            mType = bundle.getParcelable(RECORD_TYPE);
        } catch (Exception ignored) {
            mType = new RecordType(-1, "", new ArrayList<RecordView>(),
                    0, 0);
        }

        setPresenter(new RecordTypeEditPresenter());
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarBackListener(this);

        ll = findViewById(R.id.ll_record_type_edit);
        fabAdd = findViewById(R.id.fab_record_type_edit);
        fabAdd.setOnClickListener(this);
        setToolbarTitleListener(this);

        initDlg();
        initItems();
        initTitle();
    }

    private void initDlg() {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.dlg_edit, null);
        etTitle = view.findViewById(R.id.et_dlg_edit);
        etTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    setTitle(etTitle.getText().toString());
                    dlgTitle.dismiss();
                }
                return false;
            }
        });
        dlgTitle = new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("记录类型名称：")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setTitle(etTitle.getText().toString());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .create();

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

    private void initItems() {
        ll.removeAllViews();
        for (RecordView view : mType.getViews()) {
            View v = mPresenter.generateView(this, view, ll);
            ll.addView(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private void initTitle() {
        if (mType.getTitle().isEmpty()) {
            dlgTitle.show();
        } else {
            setToolbarTitle(mType.getTitle());
        }
    }

    private void setTitle(String title) {
        setToolbarTitle(title);
        mType.setTitle(title);
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
    public void onTitleClick() {
        etTitle.setText(mType.getTitle());
        etTitle.setSelection(mType.getTitle().length());
        dlgTitle.show();
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
        switch (recordTypeItem.getType()) {
            case RecordTypeItem.TYPE_TEXT:
                Intent intent = new Intent(this, TypeItemEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(TypeItemEditActivity.RECORD_TYPE, RecordTypeItem.TYPE_TEXT);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
