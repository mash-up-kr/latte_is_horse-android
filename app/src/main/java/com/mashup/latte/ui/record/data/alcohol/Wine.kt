package com.mashup.latte.ui.record.data.alcohol

import androidx.annotation.DrawableRes
import com.mashup.latte.R
import com.mashup.latte.ui.record.data.alcohol.Alcohol

/**
 * Created by Namget on 2020.02.05.
 */
data class Wine(
    @DrawableRes override val imageResource: Int = R.drawable.img_wine,
    override val typeList: MutableList<String> = arrayListOf(
        "선택안함","화이트와인", "레드와인", "로제와인"
    ),
    override val name: String = "와인",
    override val cup: Int = 0,
    override val bottle: Int = 0
) : Alcohol