package com.mashup.latte.data.datasource.remote

import com.mashup.latte.data.dto.response.DiariesResponse
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.dto.request.TokenRequest
import com.mashup.latte.data.dto.response.TokenResponse
import com.mashup.latte.data.repository.ApiRepository
import io.reactivex.Single

/**
 * Created by Namget on 2020.02.15.
 */
class ApiRemoteDataSource(
    private val apiService: ApiService
) :
    ApiRepository {

    override fun getLoginToken(tokenRequest: TokenRequest): Single<TokenResponse> =
        apiService.getLoginToken(tokenRequest)


    override fun insertAlcoholDiary(alcoholDiary: AlcoholDiary) {
        apiService.postDiary()
    }

    override fun getDiaries(): Single<DiariesResponse> {
        return apiService.requestDiaries()
    }
}