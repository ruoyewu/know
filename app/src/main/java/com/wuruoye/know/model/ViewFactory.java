package com.wuruoye.know.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuruoye.know.R;
import com.wuruoye.know.model.beans.RecordTextView;
import com.wuruoye.know.model.beans.RecordView;

/**
 * Created at 2019/3/17 21:52 by wuruoye
 * Description:
 */
public class ViewFactory {
    public static View generateView(Context context, RecordView view, ViewGroup parent) {
        if (view instanceof RecordTextView) {
            return generateTextView(context, (RecordTextView) view, parent);
        }
        return null;
    }

    private static View generateTextView(Context context, RecordTextView textView, ViewGroup parent) {
        boolean editable = textView.isEditable();
        TextView view = (TextView) LayoutInflater.from(context)
                .inflate(editable ? R.layout.view_edit :
                        R.layout.view_text, parent, false);
        view.setText(textView.getText());
        view.setTextColor(textView.getTextColor());
        view.setTextSize(textView.getTextSize());
        view.setHint(textView.getHint());
        view.setHintTextColor(textView.getHintColor());
        view.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(),
                textView.getPaddingRight(), textView.getPaddingBottom());
        ViewGroup.MarginLayoutParams params = new ViewGroup
                .MarginLayoutParams(view.getLayoutParams());
        params.setMargins(textView.getMarginLeft(), textView.getMarginTop(),
                textView.getMarginRight(), textView.getMarginBottom());
        view.setLayoutParams(params);
        view.setGravity(textView.getGravity());
        view.setTypeface(view.getTypeface(), textView.getTextStyle());
        view.setInputType(textView.getInputType());
        return view;
    }
}
