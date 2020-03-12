package com.mashup.latte.data.repository

import com.mashup.latte.data.datasource.local.ApiLocalDataSource
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.datasource.remote.ApiRemoteDataSource
import com.mashup.latte.data.dto.request.TokenRequest
import com.mashup.latte.data.dto.response.TokenResponse
import com.mashup.latte.pref.UserPref
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

/**
 * Created by Namget on 2020.02.15.
 */
class ApiRepositoryImpl(
    private val apiLocalDataSource: ApiLocalDataSource,
    private val apiRemoteDataSource: ApiRemoteDataSource
) : ApiRepository, KoinComponent {
    private val userPref: UserPref by inject()

    override fun getLoginToken(tokenRequest: TokenRequest): Single<TokenResponse> =
        apiRemoteDataSource.getLoginToken(tokenRequest)


    override fun insertAlcoholDiary(alcoholDiary: AlcoholDiary) {
        if (userPref.isSNSLogin()) {
            apiRemoteDataSource.insertAlcoholDiary(alcoholDiary)
        } else {
            apiLocalDataSource.insertAlcoholDiary(alcoholDiary)
        }
    }

    override fun getDiaries(from : Date, to : Date): Single<List<AlcoholDiary>> = apiLocalDataSource.getDiaries(from ,to)
}