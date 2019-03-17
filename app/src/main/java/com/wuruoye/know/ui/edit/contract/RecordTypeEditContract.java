package com.wuruoye.know.ui.edit.contract;

import com.wuruoye.library.contract.WIView;
import com.wuruoye.library.contract.WPresenter;

public interface RecordTypeEditContract {
    interface View extends WIView {
        void showSelectDlg();
    }

    abstract class Presenter extends WPresenter<View> {

    }
}
