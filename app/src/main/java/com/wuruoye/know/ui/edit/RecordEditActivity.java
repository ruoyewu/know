package com.wuruoye.know.ui.edit;

import android.os.Bundle;

import com.wuruoye.know.base.ToolbarActivity;

public class RecordEditActivity extends ToolbarActivity {
    public static final String RECORD_TYPE = "type";

    private int recordType;

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void initData(Bundle bundle) {
        recordType = bundle.getInt(RECORD_TYPE);
    }

    @Override
    protected void initView() {
        super.initView();

    }
}
