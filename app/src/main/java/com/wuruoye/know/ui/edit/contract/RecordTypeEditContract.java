package com.wuruoye.know.ui.edit.contract;

import com.wuruoye.know.model.beans.RecordTypeItem;
import com.wuruoye.library.contract.WIView;
import com.wuruoye.library.contract.WPresenter;

import java.util.List;

public interface RecordTypeEditContract {
    interface View extends WIView {
        void showSelectDlg();
    }

    abstract class Presenter extends WPresenter<View> {
        public abstract List<RecordTypeItem> getSelectItems();
    }
}
