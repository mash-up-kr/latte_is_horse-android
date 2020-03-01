package com.mashup.latte.data.datasource.remote

import com.mashup.latte.data.dto.response.TokenResponse
import com.mashup.latte.data.model.Token
import io.reactivex.Single
import retrofit2.http.*

interface KaKaoApiService {

    @Headers("Accept: application/json")
    @GET("/oauth/authorize")
    fun getLoginToken(
        @Query("client_id")
        clientId: String = "8886c592089a4ed719130630690f6b81",
        @Query("redirect_uri")
        redirectUrl: String = "http://ec2-54-180-89-116.ap-northeast-2.compute.amazonaws.com/api/v1/usersIP/oauth/",
        @Query("response_type")
        reponseType: String = "code"
    ): Single<TokenResponse>
}