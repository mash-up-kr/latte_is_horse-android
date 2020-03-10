package com.mashup.latte.util

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Namget on 2020.03.10.
 */
class NetworkManager() {
    fun buildMultipartWithFile(
        file: File,
        key: String
    ): MultipartBody.Part {
        var part: MultipartBody.Part? = null

        if (file.exists()) {
            val requestFile: RequestBody = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                file
            )
            part = MultipartBody.Part.createFormData(key, "", requestFile)
        }
        return part!!
    }

    fun buildMultipart(
        key: String,
        value: String
    ): MultipartBody.Part {
        var part: MultipartBody.Part? = null

        part = MultipartBody.Part.createFormData(key, value)
        return part!!
    }
}