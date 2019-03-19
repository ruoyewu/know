package com.wuruoye.know.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.wuruoye.know.R
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordView

/**
 * Created at 2019/3/17 21:52 by wuruoye
 * Description:
 */
object ViewFactory {
    fun generateView(context: Context, view: RecordView, parent: ViewGroup): View? {
        return if (view is RecordTextView) {
            generateTextView(context, view, parent)
        } else null
    }

    private fun generateTextView(context: Context, textView: RecordTextView, parent: ViewGroup): View {
        val editable = textView.isEditable
        val view = LayoutInflater.from(context)
                .inflate(if (editable)
                    R.layout.view_edit
                else
                    R.layout.view_text, parent, false) as TextView
        view.text = textView.text
        view.setTextColor(textView.textColor)
        view.textSize = textView.textSize.toFloat()
        view.setPadding(textView.paddingLeft, textView.paddingTop,
                textView.paddingRight, textView.paddingBottom)
        val params = ViewGroup.MarginLayoutParams(view.layoutParams)
        params.setMargins(textView.marginLeft, textView.marginTop,
                textView.marginRight, textView.marginBottom)
        params.width = textView.width
        params.height = textView.height
        view.layoutParams = params
        view.gravity = textView.gravity
        view.setTypeface(view.typeface, textView.textStyle)
        return view
    }
}
