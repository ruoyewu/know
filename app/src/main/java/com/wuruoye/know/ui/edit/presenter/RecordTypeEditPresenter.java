package com.wuruoye.know.ui.edit.presenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wuruoye.know.model.ViewFactory;
import com.wuruoye.know.model.beans.RecordView;
import com.wuruoye.know.ui.edit.contract.RecordTypeEditContract;
import com.wuruoye.know.model.beans.RecordTypeItem;

import java.util.Collections;
import java.util.List;

public class RecordTypeEditPresenter extends RecordTypeEditContract.Presenter {

    @Override
    public List<RecordTypeItem> getSelectItems() {
        RecordTypeItem item = new RecordTypeItem(RecordTypeItem.TYPE_TEXT, "文本");
        return Collections.singletonList(item);
    }

    @Override
    public View generateView(Context context, RecordView view, ViewGroup parent) {
        return ViewFactory.generateView(context, view, parent);
    }
}
