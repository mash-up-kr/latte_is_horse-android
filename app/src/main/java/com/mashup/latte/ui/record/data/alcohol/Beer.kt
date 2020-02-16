package com.mashup.latte.ui.record.data.alcohol

import androidx.annotation.DrawableRes
import com.mashup.latte.R
import com.mashup.latte.ui.record.data.alcohol.Alcohol

/**
 * Created by Namget on 2020.02.05.
 */
data class Beer(
    @DrawableRes override val imageResource: Int = R.drawable.img_beer,
    override val typeList: MutableList<String> = arrayListOf(
        "선택안함","생맥주 300ml", "생맥주 500ml", "카스", "하이트", "테라"
    ),
    override val name: String = "맥주",
    override val cup: Int = 0,
    override val bottle: Int = 0
) : Alcohol