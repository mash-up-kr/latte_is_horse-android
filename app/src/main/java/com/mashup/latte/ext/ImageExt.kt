package com.mashup.latte.ext

import android.graphics.BitmapFactory
import android.widget.ImageView

/**
 * Created by Namget on 2020.03.13.
 */
fun ImageView.setImageFromFilePath(path : String) {
    // Get the dimensions of the View
    val targetW: Int = 148
    val targetH: Int = 148

    val bmOptions = BitmapFactory.Options().apply {
        // Get the dimensions of the bitmap
        inJustDecodeBounds = true

        val photoW: Int = outWidth
        val photoH: Int = outHeight

        // Determine how much to scale down the image
        val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

        // Decode the image file into a Bitmap sized to fill the View
        inJustDecodeBounds = false
        inSampleSize = scaleFactor
    }
    BitmapFactory.decodeFile(path, bmOptions)?.also { bitmap ->
        this.setImageBitmap(bitmap)
    }
}