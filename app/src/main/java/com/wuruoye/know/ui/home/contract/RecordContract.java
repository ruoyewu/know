package com.wuruoye.know.ui.home.contract;

import com.wuruoye.know.util.sql.table.RecordTypeTable;
import com.wuruoye.library.contract.WIView;
import com.wuruoye.library.contract.WPresenter;

import java.util.List;

public interface RecordContract {
    interface View extends WIView {
        void showSelectTypeDlg();
    }

    abstract class Presenter extends WPresenter<View> {
        public abstract List<RecordTypeTable> getSelectType();
    }
}
