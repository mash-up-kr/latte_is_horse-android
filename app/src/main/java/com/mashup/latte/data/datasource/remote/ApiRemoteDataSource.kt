package com.mashup.latte.data.datasource.remote

import com.mashup.latte.data.dto.response.DiariesResponse
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.dto.response.TokenResponse
import com.mashup.latte.data.model.Token
import com.mashup.latte.data.repository.ApiRepository
import io.reactivex.Single
import java.lang.Exception

/**
 * Created by Namget on 2020.02.15.
 */
class ApiRemoteDataSource(val apiService: ApiService, val kaKaoApiService: KaKaoApiService) :
    ApiRepository {

    override fun getLoginToken(): Single<TokenResponse> =
        kaKaoApiService.getLoginToken()


    override fun insetAlcoholDiary(alcoholDiary: AlcoholDiary) {
        Exception("Api Remote not suppoted")
    }

    override fun getDiaries(token: String): Single<DiariesResponse> {
        return apiService.requestDiaries(token)
    }
}