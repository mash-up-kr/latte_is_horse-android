package com.mashup.latte.data.repository

import com.mashup.latte.data.dto.response.DiariesResponse
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.dto.request.TokenRequest
import com.mashup.latte.data.dto.response.TokenResponse
import io.reactivex.Single

/**
 * Created by Namget on 2020.02.15.
 */
interface ApiRepository{
    fun insertAlcoholDiary(alcoholDiary: AlcoholDiary)
    fun getLoginToken(tokenRequest: TokenRequest): Single<TokenResponse>
    fun getDiaries() : Single<List<AlcoholDiary>>
}