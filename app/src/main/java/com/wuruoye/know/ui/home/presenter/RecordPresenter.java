package com.wuruoye.know.ui.home.presenter;

import android.content.Context;

import com.wuruoye.know.model.beans.RecordType;
import com.wuruoye.know.ui.home.contract.RecordContract;
import com.wuruoye.know.util.sql.SqlUtil;

import java.util.List;

public class RecordPresenter extends RecordContract.Presenter {
    @Override
    public List<RecordType> getSelectType(Context context) {
        return SqlUtil.getInstance(context).getRecordTypeWithoutItems();
    }
}
