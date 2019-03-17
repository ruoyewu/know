package com.wuruoye.know.ui.home.contract;

import com.wuruoye.library.contract.WIView;
import com.wuruoye.library.contract.WPresenter;

public interface RecordContract {
    interface View extends WIView {

    }

    abstract class Presenter extends WPresenter<View> {

    }
}
