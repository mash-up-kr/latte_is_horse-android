package com.mashup.latte.data.datasource.remote

import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.repository.ApiRepository
import java.lang.Exception

/**
 * Created by Namget on 2020.02.15.
 */
class ApiRemoteDataSource(val apiService : ApiService) : ApiRepository{
    override fun insetAlcoholDiary(alcoholDiary: AlcoholDiary) {
        Exception("Api Remote not suppoted")
    }
}