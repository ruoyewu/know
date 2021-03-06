package com.wuruoye.know.ui.edit.presenter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.design.widget.TextInputLayout
import android.support.v4.util.ArrayMap
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.Target
import com.wuruoye.know.R
import com.wuruoye.know.model.GsonFactory
import com.wuruoye.know.model.beans.*
import com.wuruoye.know.ui.edit.contract.RecordEditContract
import com.wuruoye.know.util.sql.SqlUtil
import com.wuruoye.library.model.WConfig
import com.wuruoye.library.util.FileUtil
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Created at 2019/4/1 10:04 by wuruoye
 * Description:
 */
class RecordEditPresenter : RecordEditContract.Presenter() {
    private val gson = GsonFactory.getInstance()

    override fun getRecordType(context: Context, id: Int): RecordType {
        return SqlUtil.getInstance(context).queryRecordType(id)
    }

    override fun getRecord(context: Context, id: Int): Record {
        return SqlUtil.getInstance(context).queryRecord(id)
    }

    override fun generateRecord(context: Context, type: Int): Record {
        return Record(type)
    }

    override fun generateImgPath(): String {
        val path = WConfig.IMAGE_PATH + System.currentTimeMillis() + ".jpg"
        FileUtil.checkFile(path)
        return path
    }

    override fun loadImg(path: String, view: ImageView, recordView: RecordImageView) {
        Glide.with(view)
                .load(path)
                .apply(generateOption(recordView, view))
                .into(view)
        var item = view.getTag(R.id.tag_image)
        if (item != null) {
            item = item as RecordItem
            val imgPath = gson.fromJson<RecordImgPath>(item.content, RecordImgPath::class.java)
            imgPath.localPath = path
            imgPath.remotePath = ""
            item.content = gson.toJson(imgPath)
        } else {
            val imgPath = RecordImgPath()
            imgPath.localPath = path
            val content = gson.toJson(imgPath)

            item = RecordItem(-1, -1, -1, -1, content)
        }
        view.setTag(R.id.tag_image, item)
    }

    override fun loadRecord(context: Context, record: Record,
                            recordType: RecordType, parent: ViewGroup) {
        val recordId = record.id
        if (recordId >= 0) {
            val items = SqlUtil.getInstance(context).queryRecordItemsWithRecord(recordId)
            val map = revertItems(items)
            loadViews(recordType.views, recordId, parent, map)
        }
    }

    override fun saveRecord(context: Context, record: Record,
                            recordType: RecordType, parent: ViewGroup): Boolean {
        if (record.createTime > 0) {
            record.updateTime = System.currentTimeMillis()
        } else {
            record.createTime = System.currentTimeMillis()
        }
        val items: ArrayList<RecordItem> = ArrayList()
        saveViews(items, recordType.views, parent)
        return SqlUtil.getInstance(context).saveRecordWithItems(record, items)
    }

    private fun revertItems(items: ArrayList<RecordItem>): ArrayMap<String, RecordItem> {
        val map = ArrayMap<String, RecordItem>()
        for (item in items) {
            map[generateKey(item.type, item.typeId)] = item
        }
        return map
    }

    private fun loadViews(views: ArrayList<RecordView>, recordId: Int,
                          parent: ViewGroup, map: ArrayMap<String, RecordItem>) {
        for (i in 0 until views.size) {
            val v = views[i]
            val child = parent.getChildAt(i)
            if (v is RecordTextView && v.isEditable) {
                val item = map[generateKey(SqlUtil.ViewTableItem.getType(v), v.id)]
                if (item != null) {
                    val et = (child as TextInputLayout).editText!!
                    et.setText(item.content)
                    et.setSelection(item.content.length)
                    child.setTag(R.id.tag_text, item)
                }
            } else if (v is RecordImageView) {
                val item = map[generateKey(SqlUtil.ViewTableItem.getType(v), v.id)]
                if (item != null) {
                    val iv = child as ImageView
                    val path = gson.fromJson<RecordImgPath>(item.content, RecordImgPath::class.java)
                    Glide.with(iv)
                            .load(path.localPath)
                            .apply(generateOption(v, iv))
                            .listener(object : RequestListener<Drawable> {
                                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                    Glide.with(iv)
                                            .load(path.remotePath)
                                            .apply(generateOption(v, iv))
                                            .into(iv)
                                    return true
                                }

                                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                    return false
                                }
                            })
                            .into(iv)
                    iv.setTag(R.id.tag_image, item)
                }
            }
            else if (v is RecordLayoutView) {
                loadViews(v.views, recordId, child as ViewGroup, map)
            }
        }
    }

    private fun saveViews(items: ArrayList<RecordItem>, views: ArrayList<RecordView>,
                          parent: ViewGroup) {
        for (i in 0 until views.size) {
            val v = views[i]
            val child = parent.getChildAt(i)

            if (v is RecordTextView && v.isEditable) {
                val item = (child.getTag(R.id.tag_text) ?: RecordItem(-1, -1,
                        SqlUtil.ViewTableItem.TEXT_VIEW, v.id, "")) as RecordItem
                item.content = (child as TextInputLayout).editText!!.text.toString()
                items.add(item)
            } else if (v is RecordImageView) {
                val item = child.getTag(R.id.tag_image)
                if (item != null && item is RecordItem) {
                    if (item.id < 0) {
                        item.type = SqlUtil.ViewTableItem.IMAGE_VIEW
                        item.typeId = v.id
                    }
                    items.add(item)
                }
            } else if (v is RecordLayoutView) {
                saveViews(items, v.views, child as ViewGroup)
            }
        }
    }

    private fun generateKey(type: Int, typeId: Int): String {
        return "${type}_$typeId"
    }

    private fun generateOption(view: RecordImageView, iv: ImageView): BaseRequestOptions<*> {
        val default = RoundedCornersTransformation(0, 0)
        return bitmapTransform(MultiTransformation<Bitmap>(
                if (view.blur) BlurTransformation(25) else default,
                if (view.tint != 0) ColorFilterTransformation(view.tint) else default,
                when (view.shape) {
                    0 -> CenterCrop()
                    1 -> MultiTransformation<Bitmap>(CenterCrop(),
                            RoundedCornersTransformation(25, 0))
                    2 -> CircleCrop()
                    else -> default
                })
        )
    }
}