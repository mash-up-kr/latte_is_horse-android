package com.mashup.latte.ui.record.viewholder

import android.net.Uri
import android.view.View
import android.widget.ImageView
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import com.mashup.latte.ui.record.data.ImageTile

/**
 * Created by Namget on 2020.01.21.
 */
class GalleryViewHolder(view: View, selectedCallback: (Uri) -> Unit) :
    BaseViewHolder<ImageTile>(view) {
    private val recordImage: ImageView = view.findViewById(R.id.ImgRecordItemImage)
    private lateinit var imageUri: Uri

    init {
        view.setOnClickListener {
            if (::imageUri.isInitialized)
                selectedCallback(imageUri)
        }
    }

    override fun bind(imageTile: ImageTile) {
        imageUri = imageTile.uri
        recordImage.load(imageTile.uri)
    }
}