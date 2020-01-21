package com.mashup.latte.ui.record.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ext.e
import com.mashup.latte.ui.record.ImageTile

/**
 * Created by Namget on 2020.01.21.
 */
class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val recordImage: ImageView = view.findViewById(R.id.ImgRecordItemImage)

    fun bind(imageTile: ImageTile) {
        e("GalleryViewHolder", "${imageTile.uri}")
        recordImage.load(imageTile.uri)
    }
}