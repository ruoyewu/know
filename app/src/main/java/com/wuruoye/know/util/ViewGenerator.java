package com.wuruoye.know.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuruoye.know.R;
import com.wuruoye.know.util.sql.table.TextViewTable;
import com.wuruoye.know.util.sql.table.ViewTable;

/**
 * Created at 2019/3/17 21:52 by wuruoye
 * Description:
 */
public class ViewGenerator {
    public static View generateView(Context context, ViewTable table, ViewGroup parent) {
        if (table instanceof TextViewTable) {
            return generateTextView(context, (TextViewTable) table, parent);
        }
        return null;
    }

    private static View generateTextView(Context context, TextViewTable table, ViewGroup parent) {
        boolean editable = table.isEditable();
        TextView view = (TextView) LayoutInflater.from(context)
                .inflate(editable ? R.layout.view_edit :
                        R.layout.view_text, parent, false);
        view.setText(table.getText());
        view.setTextColor(table.getTextColor());
        view.setTextSize(table.getTextSize());
        view.setHint(table.getHint());
        view.setHintTextColor(table.getHintColor());
        view.setPadding(table.getPaddingLeft(), table.getPaddingTop(),
                table.getPaddingRight(), table.getPaddingBottom());
        ViewGroup.MarginLayoutParams params = new ViewGroup
                .MarginLayoutParams(view.getLayoutParams());
        params.setMargins(table.getMarginLeft(), table.getMarginTop(),
                table.getMarginRight(), table.getMarginBottom());
        view.setLayoutParams(params);
        view.setGravity(table.getGravity());
        view.setTypeface(view.getTypeface(), table.getTextStyle());
        view.setInputType(table.getInputType());
        return view;
    }
}
