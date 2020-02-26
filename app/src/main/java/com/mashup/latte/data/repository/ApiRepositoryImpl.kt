package com.mashup.latte.data.repository

import com.mashup.latte.data.datasource.local.ApiLocalDataSource
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.datasource.remote.ApiRemoteDataSource
import org.koin.core.KoinComponent

/**
 * Created by Namget on 2020.02.15.
 */
class ApiRepositoryImpl(
    private val apiLocalDataSource: ApiLocalDataSource,
    private val apiRemoteDataSource: ApiRemoteDataSource
) : ApiRepository{


    override fun insetAlcoholDiary(alcoholDiary: AlcoholDiary) =
        apiLocalDataSource.appDao.insertAlcoholDiary(alcoholDiary)
    override fun getDiaries(token: String) =
        apiRemoteDataSource.apiService.requestDiaries(token)
}