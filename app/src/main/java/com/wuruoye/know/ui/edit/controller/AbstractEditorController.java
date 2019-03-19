package com.wuruoye.know.ui.edit.controller;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.wuruoye.know.R;
import com.wuruoye.know.ui.edit.controller.EditorController;
import com.wuruoye.know.widget.BottomDialog;
import com.wuruoye.know.widget.TouchMarginView;
import com.wuruoye.library.util.DensityUtil;

/**
 * Created at 2019/3/18 17:16 by wuruoye
 * Description:
 */
public abstract class AbstractEditorController implements EditorController {
    protected Context mContext;

    protected AlertDialog dlgEdit;

    protected Dialog dlgMargin;
    protected TouchMarginView tmv;

    void attach(Context context) {
        mContext = context;

        initDlg();
    }

    private void initDlg() {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.dlg_margin, null);
        tmv = view.findViewById(R.id.tmv_dlg_margin);
        dlgMargin = new BottomDialog(mContext, view);
    }
}
