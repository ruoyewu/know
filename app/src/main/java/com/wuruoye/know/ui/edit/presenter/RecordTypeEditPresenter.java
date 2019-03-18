package com.wuruoye.know.ui.edit.presenter;

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
}
