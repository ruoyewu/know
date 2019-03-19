package com.wuruoye.know.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wuruoye.know.R;

/**
 * Created at 2019/3/18 23:02 by wuruoye
 * Description:
 */
public class BottomDialog extends Dialog {
    public BottomDialog(@NonNull Context context, View view) {
        super(context, R.style.BottomDlg);

        setContentView(view);
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog);
        }
    }
}
