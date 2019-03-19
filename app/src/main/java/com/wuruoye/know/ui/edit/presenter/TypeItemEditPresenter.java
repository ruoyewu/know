package com.wuruoye.know.ui.edit.presenter;

import com.wuruoye.know.model.beans.RecordView;
import com.wuruoye.know.ui.edit.controller.EditorController;
import com.wuruoye.know.ui.edit.contract.TypeItemEditContract;
import com.wuruoye.know.ui.edit.controller.TextViewController;

/**
 * Created at 2019/3/18 16:05 by wuruoye
 * Description:
 */
public class TypeItemEditPresenter extends TypeItemEditContract.Presenter {

    @Override
    public EditorController generateController(int type, RecordView view) {
        return new TextViewController(view);
    }
}
