package com.wuruoye.know.model

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.design.widget.TextInputLayout
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

import com.wuruoye.know.R
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.library.util.DensityUtil

/**
 * Created at 2019/3/17 21:52 by wuruoye
 * Description:
 */
object ViewFactory {
    fun generateView(context: Context, view: RecordView, parent: ViewGroup): View? {
        return ViewFactory.generateView(context, view, parent, true)
    }

    fun generateView(context: Context, view: RecordView, parent: ViewGroup, attach: Boolean): View? {
        return if (view is RecordTextView) {
            generateTextView(context, view, parent, attach)
        } else null
    }

    private fun generateTextView(context: Context, textView: RecordTextView, parent: ViewGroup,
                                 attach: Boolean): View {
        with(textView) {
            val viewLayout = LayoutInflater.from(context)
                    .inflate(if (isEditable)
                        R.layout.view_edit
                    else
                        R.layout.view_text, parent, false)

            if (isEditable) {
                val layout = viewLayout as TextInputLayout
                layout.hint = hint
                // TODO set hint size and color using reflect
            }

            val view = if (isEditable)
                viewLayout.findViewById<EditText>(R.id.et_view_edit)
                    else
                viewLayout as TextView
            view.text = text
            view.setTextColor(textColor)
            view.textSize = textSize.toFloat()
            viewLayout.setBackgroundColor(bgColor)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewLayout.foreground = ColorDrawable(fgColor)
            }
            viewLayout.setPadding(DensityUtil.dp2px(context, paddingLeft.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingTop.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingRight.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingBottom.toFloat()).toInt())
            val params = ViewGroup.MarginLayoutParams(viewLayout.layoutParams)
            params.setMargins(DensityUtil.dp2px(context, marginLeft.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginTop.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginRight.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginBottom.toFloat()).toInt())
            params.width = width
            params.height = height
            viewLayout.layoutParams = params
            view.gravity = gravity
            view.setTypeface(view.typeface, textStyle)
            view.inputType = inputType
            view.minLines = minLine
            view.maxLines = maxLine
            view.movementMethod = ScrollingMovementMethod.getInstance()

            if (attach) {
                parent.addView(viewLayout)
            }
            return viewLayout
        }
    }
}
