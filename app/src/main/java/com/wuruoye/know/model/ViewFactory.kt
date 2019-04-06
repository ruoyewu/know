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
import android.widget.LinearLayout
import android.widget.TextView

import com.wuruoye.know.R
import com.wuruoye.know.model.beans.RecordLayoutView
import com.wuruoye.know.model.beans.RecordTextView
import com.wuruoye.know.model.beans.RecordView
import com.wuruoye.library.util.DensityUtil

/**
 * Created at 2019/3/17 21:52 by wuruoye
 * Description:
 */
object ViewFactory {
    interface OnLongClickListener {
        fun onLongClick(recordView: RecordView)
    }

    fun generateView(context: Context, view: RecordView, parent: ViewGroup): View? {
        return generateView(context, view, parent, true, null)
    }

    fun generateView(context: Context, view: RecordView,
                     parent: ViewGroup, attach: Boolean): View? {
        return generateView(context, view, parent, attach, null)
    }

    fun generateView(context: Context, view: RecordView, parent: ViewGroup, attach: Boolean,
                     listener: OnLongClickListener?): View? {
        return when (view) {
            is RecordTextView -> generateTextView(context, view, parent, attach, listener)
            is RecordLayoutView -> generateLayoutView(context, view, parent, attach, listener)
            else -> null
        }
    }

    private fun generateTextView(context: Context, textView: RecordTextView, parent: ViewGroup,
                                 attach: Boolean, listener: OnLongClickListener?): View {
        with(textView) {
            if (isEditable) {
                val viewLayout = LayoutInflater.from(context)
                        .inflate(R.layout.view_edit, parent, false) as TextInputLayout

                viewLayout.hint = hint
                // TODO set hint size and color using reflect
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

                val view = viewLayout.findViewById<EditText>(R.id.et_view_edit)
                view.setTextColor(textColor)
                view.textSize = textSize.toFloat()
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
            } else {
                val view = LayoutInflater.from(context)
                        .inflate(R.layout.view_text, parent, false) as TextView
                view.text = text
                view.setTextColor(textColor)
                view.textSize = textSize.toFloat()
                view.setBackgroundColor(bgColor)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.foreground = ColorDrawable(fgColor)
                }
                view.setPadding(DensityUtil.dp2px(context, paddingLeft.toFloat()).toInt(),
                        DensityUtil.dp2px(context, paddingTop.toFloat()).toInt(),
                        DensityUtil.dp2px(context, paddingRight.toFloat()).toInt(),
                        DensityUtil.dp2px(context, paddingBottom.toFloat()).toInt())
                val params = ViewGroup.MarginLayoutParams(view.layoutParams)
                params.setMargins(DensityUtil.dp2px(context, marginLeft.toFloat()).toInt(),
                        DensityUtil.dp2px(context, marginTop.toFloat()).toInt(),
                        DensityUtil.dp2px(context, marginRight.toFloat()).toInt(),
                        DensityUtil.dp2px(context, marginBottom.toFloat()).toInt())
                params.width = width
                params.height = height
                view.layoutParams = params
                view.gravity = gravity
                view.setTypeface(view.typeface, textStyle)
                view.minLines = minLine
                view.maxLines = maxLine
                view.movementMethod = ScrollingMovementMethod.getInstance()

                if (attach) {
                    parent.addView(view)
                }
                if (listener != null) {
                    view.setOnLongClickListener {
                        listener.onLongClick(textView)
                        true
                    }
                }
                return view
            }
        }
    }

    private fun generateLayoutView(context: Context, layoutView: RecordLayoutView,
                                   parent: ViewGroup, attach: Boolean,
                                   listener: OnLongClickListener?): View {
        with(layoutView) {
            val view = LayoutInflater.from(context).inflate(R.layout.view_layout,
                    parent, false) as LinearLayout
            view.orientation = orientation
            view.gravity = gravity
            view.setBackgroundColor(bgColor)
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
            val lp = view.layoutParams as ViewGroup.MarginLayoutParams
            lp.height = height
            lp.width = width
            lp.setMargins(marginLeft, marginTop, marginRight, marginBottom)
            view.layoutParams = lp

            for (v in views) {
                generateView(context, v, view, true, listener)
            }

            if (attach) {
                parent.addView(view)
            }
            if (listener != null) {
                view.setOnLongClickListener {
                    listener.onLongClick(layoutView)
                    true
                }
            }
            return view
        }
    }
}
