package com.mashup.latte.data.datasource.remote

import com.mashup.latte.data.dto.response.DiariesResponse
import com.mashup.latte.data.dto.response.TokenResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("/api/v1/diaries")
    fun requestDiaries(
        @Header("Authorization") token: String
    ): Single<DiariesResponse>

    @GET("/api/v1/usersIP/oauth/")
    fun getLoginToken(): Single<TokenResponse>

}