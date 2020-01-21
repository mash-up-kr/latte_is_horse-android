package com.mashup.latte.ui.record

import androidx.annotation.IntDef
import java.io.File


/**
 * Created by Namget on 2020.01.21.
 */
//class ImageTile(val uri: Uri, @TileType private val tileType: Int = IMAGE) {
class ImageTile(val uri: File, @TileType private val tileType: Int = IMAGE) {


    companion object {
        const val IMAGE = 1
        const val CAMERA = 2
        const val GALLERY = 3
    }

    fun isImaeTile() = tileType == IMAGE
    fun isCameraTile() = tileType == CAMERA
    fun isGalleryTile() = tileType == GALLERY

    @IntDef(
        IMAGE,
        CAMERA,
        GALLERY
    )
    @Retention(AnnotationRetention.SOURCE)
    private annotation class TileType
}