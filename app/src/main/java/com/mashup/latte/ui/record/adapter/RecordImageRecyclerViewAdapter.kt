package com.mashup.latte.ui.record.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ext.toastMakeToast
import com.mashup.latte.ui.record.data.ImageTile
import com.mashup.latte.ui.record.view.RecordFrameLayout
import com.mashup.latte.ui.record.viewholder.GalleryViewHolder
import com.mashup.latte.ui.record.viewholder.TakePictureViewHolder

/**
 * Created by Namget on 2020.01.21.
 */
class RecordImageRecyclerViewAdapter(
    private val context: Context,
    private val galleryCallback: (Uri) -> Unit, //선택된 갤러리의 이미지를 보여준다.
    private val takePictureCallback: () -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val imageTiles = arrayListOf<ImageTile>()
    private val _selectedUri = arrayListOf<Uri>()

    init {
        initGallery()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TAKE_PICTURE_TYPE else IMAGE_TILE_TYPE
    }

    fun clearSelectedUri() {
//        for(imageTile in imageTiles){
//            imageTile.
//        }
        _selectedUri.clear()
    }

    fun getUriList(): List<Uri> {
        return _selectedUri
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TAKE_PICTURE_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_record_take_picture, parent, false)
                TakePictureViewHolder(view) {
                    takePictureCallback()
                }
            }
            IMAGE_TILE_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_record_gallery, parent, false)
                GalleryViewHolder(view) { uri ->
                    setSelected(uri, view as RecordFrameLayout)
                }
            }
            else -> {
                throw java.lang.Exception("viewType is not defined")
            }
        }
    }

    @SuppressLint("NewApi")
    private fun setSelected(uri: Uri, recordImage: FrameLayout) {

        //document bug
        //선택되지 않은 이미지
        if (recordImage.foreground == null) {
            //최대 추가갯수보다 적다면 추가
            if (_selectedUri.size < MAX_SELECTABLE_COUNT) {
                recordImage.foreground =
                    context.applicationContext.getDrawable(R.drawable.shape_rectangle_white_transparent)
                _selectedUri.add(uri)
                // 선택된 갤러리의 사진을 보여줌
                galleryCallback(uri)
            } else {
                context.toastMakeToast(
                    String.format(
                        context.getString(R.string.record_image_max_count1),
                        3
                    )
                )
            }
        }
        //이미 선택된 이미지
        else {
            recordImage.foreground = null
            if (_selectedUri.contains(uri)) {
                _selectedUri.remove(uri)

                //마지막에 선택된 사진을 보여줌
                if (_selectedUri.size > 0) {
                    galleryCallback(_selectedUri.last())
                }
            }
        }

    }

    override fun getItemCount(): Int = imageTiles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is GalleryViewHolder) {
            holder.bind(imageTiles[position])
        }
    }

    private fun initGallery() {
        var cursor: Cursor? = null
        try {
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val columns = arrayOf(MediaStore.Images.Media._ID)
            val orderBy = "${MediaStore.Images.Media.DATE_ADDED} DESC"
            cursor =
                context.applicationContext.contentResolver.query(uri, columns, null, null, orderBy)
            if (cursor != null) {
                var count = 0

                while (cursor.moveToNext() && count < PREVIEW_MAXIMUM_COUNT) {
                    imageTiles.add(
                        ImageTile(
                            Uri.parse(
                                "${uri}/${cursor.getString(0)}"
                            )
                        )
                    )
                    count++
                }
            }
        } catch (e: Exception) {
            com.mashup.latte.ext.e(TAG, "error ", e)
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
    }


    companion object {
        const val PREVIEW_MAXIMUM_COUNT = 300
        const val MAX_SELECTABLE_COUNT = 3
        const val TAG = "RecordImageRecyclerViewAdapter"
        const val TAKE_PICTURE_TYPE = 1
        const val IMAGE_TILE_TYPE = 2
    }
}