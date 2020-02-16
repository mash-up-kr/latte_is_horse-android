package com.mashup.latte.data.datasource.remote

import com.mashup.latte.data.Diary
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/v1/diaries")
    fun requestDiaries(
        @Query("token") token: String
    ): Single<List<Diary>>

}