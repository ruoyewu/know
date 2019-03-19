package com.wuruoye.know.ui.edit.contract;

import com.wuruoye.know.model.beans.RecordView;
import com.wuruoye.know.ui.edit.controller.EditorController;
import com.wuruoye.library.contract.WIView;
import com.wuruoye.library.contract.WPresenter;

/**
 * Created at 2019/3/18 15:32 by wuruoye
 * Description:
 */
public interface TypeItemEditContract {
    interface View extends WIView {
    }

    abstract class Presenter extends WPresenter<View> {
        public abstract EditorController generateController(int type, RecordView view);
    }
}
