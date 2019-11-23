package com.mashup.latte.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    private const val BASE_URL = ""

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createApi() : RetrofitApi {
        return retrofit.create(com.mashup.latte.retrofit.RetrofitApi::class.java)
    }
}