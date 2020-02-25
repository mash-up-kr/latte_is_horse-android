package com.mashup.latte.data.datasource.local

import com.mashup.latte.data.DiariesResponse
import com.mashup.latte.data.datasource.local.dao.AppDao
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.repository.ApiRepository
import io.reactivex.Single
import java.lang.Exception

/**
 * Created by Namget on 2020.02.15.
 */
class ApiLocalDataSource(val appDao: AppDao) : ApiRepository {

    override fun insetAlcoholDiary(alcoholDiary: AlcoholDiary) =
        appDao.insertAlcoholDiary(alcoholDiary)
    override fun getDiaries(token: String): Single<DiariesResponse> {
        Exception("Api Local not suppoted")
        return Single.just(DiariesResponse(
            0,
            null,
            null,
            ArrayList()
        ))
    }
}