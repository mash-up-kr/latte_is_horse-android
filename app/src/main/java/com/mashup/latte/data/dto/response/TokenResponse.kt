package com.mashup.latte.data.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Namget on 2020.02.27.
 */
data class TokenResponse(
    @SerializedName("token")
    val token: String)