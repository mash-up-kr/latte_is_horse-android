package com.mashup.latte.data.datasource.local

import com.mashup.latte.data.dto.response.DiariesResponse
import com.mashup.latte.data.datasource.local.dao.AppDao
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.dto.request.TokenRequest
import com.mashup.latte.data.dto.response.TokenResponse
import com.mashup.latte.data.repository.ApiRepository
import io.reactivex.Single
import java.lang.Exception

/**
 * Created by Namget on 2020.02.15.
 */
class ApiLocalDataSource(val appDao: AppDao) : ApiRepository {

    override fun insertAlcoholDiary(alcoholDiary: AlcoholDiary) =
        appDao.insertAlcoholDiary(alcoholDiary)

    override fun getDiaries(token: String): Single<DiariesResponse> {
        Exception("Api Local not suppoted")
        return Single.just(
            DiariesResponse(
                0,
                null,
                null,
                ArrayList()
            )
        )
    }

    override fun getLoginToken(tokenRequest: TokenRequest): Single<TokenResponse> {
        throw IllegalAccessException("local api not supported")
    }
}