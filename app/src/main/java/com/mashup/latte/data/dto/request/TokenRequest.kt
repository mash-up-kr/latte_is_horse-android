package com.mashup.latte.data.dto.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Namget on 2020.03.05.
 */
data class TokenRequest(
    @SerializedName("grant_type")
    val grantType: String = "convert_token",
    @SerializedName("client_id")
    val clientId: String = "948gKi2CCjBDmpqsRdc2veWePBBvA3q1BrwU1FQz",
    @SerializedName("backend")
    val backend: String = "kakao",
    @SerializedName("token")
    val token: String
)