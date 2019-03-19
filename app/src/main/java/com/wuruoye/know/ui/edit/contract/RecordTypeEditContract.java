package com.wuruoye.know.ui.edit.contract;

import android.content.Context;
import android.view.ViewGroup;

import com.wuruoye.know.model.beans.RecordTypeItem;
import com.wuruoye.know.model.beans.RecordView;
import com.wuruoye.library.contract.WIView;
import com.wuruoye.library.contract.WPresenter;

import java.util.List;

public interface RecordTypeEditContract {
    interface View extends WIView {
        void showSelectDlg();
    }

    abstract class Presenter extends WPresenter<View> {
        public abstract List<RecordTypeItem> getSelectItems();
        public abstract android.view.View generateView(Context context, RecordView view, ViewGroup parent);
    }
}
