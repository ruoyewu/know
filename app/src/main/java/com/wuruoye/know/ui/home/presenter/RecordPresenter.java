package com.wuruoye.know.ui.home.presenter;

import com.wuruoye.know.ui.home.contract.RecordContract;
import com.wuruoye.know.util.sql.table.RecordTypeTable;

import java.util.Arrays;
import java.util.List;

public class RecordPresenter extends RecordContract.Presenter {
    @Override
    public List<RecordTypeTable> getSelectType() {
        RecordTypeTable t1 = new RecordTypeTable(1, "文笔", "1,2,3,4,5");
        RecordTypeTable t2 = new RecordTypeTable(2, "书情", "1,2,3,4,5");
        return Arrays.asList(t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2, t1, t2);
    }
}
