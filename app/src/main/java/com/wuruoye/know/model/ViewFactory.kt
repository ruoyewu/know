package com.wuruoye.know.model

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.design.widget.TextInputLayout
import android.support.v4.app.ActivityCompat
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.wuruoye.know.R
import com.wuruoye.know.model.beans.RecordImageView
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
        fun onLongClick(recordView: RecordView, view: View,
                        parentView: ArrayList<RecordView>, parent: ViewGroup)
    }

    interface OnClickListener {
        fun onClick(recordView: RecordView, view: View)
    }

    fun generateView(context: Context, view: RecordView, parent: ViewGroup): View? {
        return generateView(context, view, parent, true, null)
    }

    fun generateView(context: Context, view: RecordView, parent: ViewGroup,
                     attach: Boolean): View? {
        return ViewFactory.generateView(context, view, parent, attach, null)
    }

    fun generateView(context: Context, view: RecordView, parent: ViewGroup,
                     attach: Boolean, listener: OnClickListener?): View? {
        return when (view) {
            is RecordTextView -> generateTextView(context, view, parent, attach)
            is RecordLayoutView -> generateLayoutView(context, view, parent, attach, listener)
            is RecordImageView -> generateImageView(context, view, parent, attach, listener)
            else -> null
        }
    }

    private fun generateTextView(context: Context, textView: RecordTextView, parent: ViewGroup,
                                 attach: Boolean): View {
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
                params.width = lengthToPx(context, width)
                params.height = lengthToPx(context, height)
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
                params.width = lengthToPx(context, width)
                params.height = lengthToPx(context, height)
                view.layoutParams = params
                view.gravity = gravity
                view.setTypeface(view.typeface, textStyle)
                view.minLines = minLine
                view.maxLines = maxLine
                view.movementMethod = ScrollingMovementMethod.getInstance()

                if (attach) {
                    parent.addView(view)
                }
                return view
            }
        }
    }

    private fun generateImageView(context: Context, imageView: RecordImageView, parent: ViewGroup,
                                  attach: Boolean, listener: OnClickListener?): View {
        with(imageView) {
            val view = LayoutInflater.from(context)
                    .inflate(R.layout.view_img, parent, false) as ImageView
            view.setPadding(DensityUtil.dp2px(context, paddingLeft.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingTop.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingRight.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingBottom.toFloat()).toInt())

            val lp = view.layoutParams as ViewGroup.MarginLayoutParams
            lp.width = lengthToPx(context, width)
            lp.height = lengthToPx(context, height)
            lp.setMargins(DensityUtil.dp2px(context, marginLeft.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginTop.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginRight.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginBottom.toFloat()).toInt())
            view.layoutParams = lp

            if (attach) {
                parent.addView(view)
            }

            if (listener != null) {
                view.setOnClickListener {
                    listener.onClick(imageView, view)
                }
            }

            return view
        }
    }

    private fun generateLayoutView(context: Context, layoutView: RecordLayoutView,
                                   parent: ViewGroup, attach: Boolean,
                                   listener: OnClickListener?): View {
        with(layoutView) {
            val view = LayoutInflater.from(context).inflate(R.layout.view_layout,
                    parent, false) as LinearLayout
            view.orientation = orientation
            view.gravity = gravity
            view.setBackgroundColor(bgColor)
            view.setPadding(DensityUtil.dp2px(context, paddingLeft.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingTop.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingRight.toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingBottom.toFloat()).toInt())
            val lp = view.layoutParams as ViewGroup.MarginLayoutParams
            lp.width = lengthToPx(context, width)
            lp.height = lengthToPx(context, height)
            lp.setMargins(DensityUtil.dp2px(context, marginLeft.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginTop.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginRight.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginBottom.toFloat()).toInt())
            view.layoutParams = lp

            for (v in views) {
                generateView(context, v, view, true, listener)
            }

            if (attach) {
                parent.addView(view)
            }
            return view
        }
    }

    fun generateEditView(context: Context, recordView: RecordView, parent: ViewGroup,
                         parentView: ArrayList<RecordView>, attach: Boolean,
                         listener: OnLongClickListener?): View? {
        if (recordView is RecordLayoutView) {
            return generateEditLayoutView(context, recordView, parent, parentView, attach, listener)
        } else {
            with(recordView) {
                val view = LayoutInflater.from(context)
                        .inflate(R.layout.view_text, parent, false) as TextView

                view.setBackgroundColor(ActivityCompat.getColor(context, R.color.transparent_platinum))
                view.setPadding(DensityUtil.dp2px(context, (paddingLeft+5).toFloat()).toInt(),
                        DensityUtil.dp2px(context, (paddingTop+5).toFloat()).toInt(),
                        DensityUtil.dp2px(context, (paddingRight+5).toFloat()).toInt(),
                        DensityUtil.dp2px(context, (paddingBottom+5).toFloat()).toInt())
                view.text = getLabel(recordView)
                view.gravity = Gravity.CENTER

                val lp = view.layoutParams as ViewGroup.MarginLayoutParams
                lp.width = lengthToPx(context, width)
                lp.height = lengthToPx(context, height)
                lp.setMargins(DensityUtil.dp2px(context, marginLeft.toFloat()).toInt(),
                        DensityUtil.dp2px(context, marginTop.toFloat()).toInt(),
                        DensityUtil.dp2px(context, marginRight.toFloat()).toInt(),
                        DensityUtil.dp2px(context, marginBottom.toFloat()).toInt())
                view.layoutParams = lp

                if (attach) {
                    parent.addView(view)
                }

                if (listener != null) {
                    view.setOnLongClickListener {
                        listener.onLongClick(recordView, view, parentView, parent)
                        true
                    }
                }

                return view
            }
        }
    }

    private fun generateEditLayoutView(context: Context, layoutView: RecordLayoutView,
                                       parent: ViewGroup, parentView: ArrayList<RecordView>,
                                       attach: Boolean, listener: OnLongClickListener?): View {
        with(layoutView) {
            val view = LayoutInflater.from(context)
                    .inflate(R.layout.view_layout, parent, false) as LinearLayout
            view.setBackgroundColor(ActivityCompat.getColor(context, R.color.transparent_platinum))
            view.setPadding(DensityUtil.dp2px(context, paddingLeft.toFloat()).toInt(),
                    DensityUtil.dp2px(context, (paddingTop+10).toFloat()).toInt(),
                    DensityUtil.dp2px(context, paddingRight.toFloat()).toInt(),
                    DensityUtil.dp2px(context, (paddingBottom+10).toFloat()).toInt())
            view.gravity = gravity
            view.orientation = orientation

            val lp = view.layoutParams as ViewGroup.MarginLayoutParams
            lp.width = lengthToPx(context, width)
            lp.height = lengthToPx(context, height)
            lp.setMargins(DensityUtil.dp2px(context, marginLeft.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginTop.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginRight.toFloat()).toInt(),
                    DensityUtil.dp2px(context, marginBottom.toFloat()).toInt())
            view.layoutParams = lp

            for (v in views) {
                generateEditView(context, v, view, layoutView.views, true, listener)
            }

            if (attach) {
                parent.addView(view)
            }

            if (listener != null) {
                view.setOnLongClickListener {
                    listener.onLongClick(layoutView, view, parentView, parent)
                    true
                }
            }

            return view
        }
    }

    private fun lengthToPx(context: Context, length: Int): Int {
        return if (length < 0) length
                else DensityUtil.dp2px(context, length.toFloat()).toInt()
    }

    private fun getLabel(view: RecordView): String {
        return when (view) {
            is RecordImageView -> "图片"
            is RecordTextView -> if (view.isEditable) "编辑框" else "标签"
            else -> "未知"
        }
    }
}
