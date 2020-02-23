package com.mashup.latte.data.repository

import com.mashup.latte.data.datasource.local.entity.AlcoholDiary

/**
 * Created by Namget on 2020.02.15.
 */
interface ApiRepository{
    fun insetAlcoholDiary(alcoholDiary: AlcoholDiary)
}