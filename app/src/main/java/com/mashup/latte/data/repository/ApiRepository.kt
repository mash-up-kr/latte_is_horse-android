package com.mashup.latte.data.repository

import com.mashup.latte.data.dto.response.DiariesResponse
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.dto.response.TokenResponse
import com.mashup.latte.data.model.Token
import io.reactivex.Single

/**
 * Created by Namget on 2020.02.15.
 */
interface ApiRepository{
    fun insertAlcoholDiary(alcoholDiary: AlcoholDiary)
    fun getLoginToken() : Single<TokenResponse>
    fun getDiaries(token: String) : Single<DiariesResponse>
}