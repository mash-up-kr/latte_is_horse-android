package com.mashup.latte.data.repository

import com.mashup.latte.data.datasource.local.ApiLocalDataSource
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.datasource.remote.ApiRemoteDataSource
import com.mashup.latte.pref.UserPref

/**
 * Created by Namget on 2020.02.15.
 */
class ApiRepositoryImpl(
    private val apiLocalDataSource: ApiLocalDataSource,
    private val apiRemoteDataSource: ApiRemoteDataSource
) : ApiRepository{


    override fun insetAlcoholDiary(alcoholDiary: AlcoholDiary) {

        if(UserPref.isSNSLogin()) {
            apiLocalDataSource.appDao.insertAlcoholDiary(alcoholDiary)
        }else{
            apiLocalDataSource.appDao.insertAlcoholDiary(alcoholDiary)
        }
    }
    override fun getDiaries(token: String) =
        apiRemoteDataSource.apiService.requestDiaries(token)
}