package com.mashup.latte.ui.record.adapter

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ext.e
import com.mashup.latte.ui.record.ImageTile
import com.mashup.latte.ui.record.viewholder.GalleryViewHolder
import java.io.File

/**
 * Created by Namget on 2020.01.21.
 */
class RecordImageRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<GalleryViewHolder>() {
    private val imageTiles = arrayListOf<ImageTile>()

    init {
        initGallery()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_record_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun getItemCount(): Int = imageTiles.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(imageTiles[position])
    }

    private fun initGallery() {
        var cursor: Cursor? = null
        try {
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val columns = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA
            )
            val orderBy = "${MediaStore.Images.Media.DATE_ADDED} DESC"

            cursor =
                context.applicationContext.contentResolver.query(uri, columns, null, null, orderBy)

            if (cursor != null) {
                var count = 0
                e("size", "cursor $count")

                while (cursor.moveToNext() && count < PREVIEW_MAXIMUM_COUNT) {
                    val data = MediaStore.Images.Media.DATA
                    val imageLocation = cursor.getString(cursor.getColumnIndex(data))

                    e("size", "imageLocation $imageLocation")
                    val file = File(imageLocation)
//                    imageTiles.add(ImageTile(Uri.fromFile(file)))
                    imageTiles.add(ImageTile(file))
                    count++
                }
            }
        } catch (e: Exception) {
            e("size", "imageTile error ${e}")

        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }

        e("size", "imageTile ${imageTiles.size}")

    }

    companion object {
        const val PREVIEW_MAXIMUM_COUNT = 10
    }
}