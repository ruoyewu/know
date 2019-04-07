package com.wuruoye.know.ui.edit.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.wuruoye.know.R
import com.wuruoye.know.model.ViewFactory
import com.wuruoye.know.model.beans.RecordImageView
import com.wuruoye.know.model.beans.RecordView

/**
 * Created at 2019/4/6 20:46 by wuruoye
 * Description:
 */
class ImageViewController (private val mView: RecordImageView)
    : AbstractEditorController(), View.OnClickListener {
    private lateinit var flContent: FrameLayout
    private lateinit var svOptions: ScrollView

    private lateinit var mShowView: ImageView
    private lateinit var llScaleType: LinearLayout
    private lateinit var tvScaleType: TextView
    private lateinit var llShape: LinearLayout
    private lateinit var tvShape: TextView

    override fun attach(context: Context, fl: FrameLayout, sv: ScrollView) {
        super.attach(context)
        flContent = fl
        svOptions = sv

        initView()
    }

    private fun initView() {
        mShowView = ViewFactory.generateView(mContext, mView, flContent) as ImageView
        LayoutInflater.from(mContext)
                .inflate(R.layout.layout_image_view, svOptions, true)

        with(svOptions) {
            llWidth = findViewById(R.id.ll_width_layout_image)
            llHeight = findViewById(R.id.ll_height_layout_image)
            llScaleType = findViewById(R.id.ll_scale_type_layout_image)
            llShape = findViewById(R.id.ll_shape_layout_image)
            llMargin = findViewById(R.id.ll_margin_layout_image)
            llPadding = findViewById(R.id.ll_padding_layout_image)

            tvWidth = findViewById(R.id.tv_width_layout_image)
            tvHeight = findViewById(R.id.tv_height_layout_image)
            tvScaleType = findViewById(R.id.tv_scale_type_layout_image)
            tvShape = findViewById(R.id.tv_shape_layout_image)
            tvMargin = findViewById(R.id.tv_margin_layout_image)
            tvPadding = findViewById(R.id.tv_padding_layout_image)
        }

        llScaleType.setOnClickListener(this)
        llShape.setOnClickListener(this)

        with(mView) {
            tvScaleType.text = SCALE_TYPE_NAME[scaleType]
            tvShape.text = SHAPE_NAME[shape]
            mShowView.scaleType = IMG_SCALE_TYPE[scaleType]
            // TODO set shape
        }

        super.initView(mView, mShowView)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.ll_scale_type_layout_image -> {
                mCurType = TYPE_SCALE_TYPE
                showSelectDlg(SCALE_TYPE_NAME, mView.scaleType)
            }
            R.id.ll_shape_layout_image -> {
                mCurType = TYPE_SHAPE
                showSelectDlg(SHAPE_NAME, mView.shape)
            }
        }
    }

    override fun onItemSelect(value: Int) {
        super.onItemSelect(value)
        when(mCurType) {
            TYPE_SCALE_TYPE -> {
                mView.scaleType = value
                tvScaleType.text = SCALE_TYPE_NAME[value]

                mShowView.scaleType = IMG_SCALE_TYPE[value]
            }
            TYPE_SHAPE -> {
                mView.shape = value
                tvShape.text = SHAPE_NAME[value]

                // TODO 设置图片形状
            }
        }
    }

    override val result: RecordView
        get() = mView
}