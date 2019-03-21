package com.wuruoye.know.model

import android.content.Context
import android.text.method.ScrollingMovementMethod
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
        with(textView) {
            val view = LayoutInflater.from(context)
                    .inflate(if (isEditable)
                        R.layout.view_edit
                    else
                        R.layout.view_text, parent, false) as TextView
            view.text = text
            view.setTextColor(textColor)
            view.textSize = textSize.toFloat()
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
            val params = ViewGroup.MarginLayoutParams(view.layoutParams)
            params.setMargins(marginLeft, marginTop, marginRight, marginBottom)
            params.width = width
            params.height = height
            view.layoutParams = params
            view.gravity = gravity
            view.setTypeface(view.typeface, textStyle)
            view.minLines = minLine
            view.maxLines = maxLine
            view.movementMethod = ScrollingMovementMethod.getInstance()
            return view
        }
    }
}
