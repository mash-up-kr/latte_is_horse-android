package com.mashup.latte.util

import android.content.Context
import java.io.File

/**
 * Created by Namget on 2020.03.10.
 */
class FileManager (val context : Context){

    fun getInternalFile(){
        var yourFilePath: String = context.filesDir.toString() + "/" + "image_1583837171273_0.jpg"
        var yourFile: File = File(yourFilePath)
    }

}