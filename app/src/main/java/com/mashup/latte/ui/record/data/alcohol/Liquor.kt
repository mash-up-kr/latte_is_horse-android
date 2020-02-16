package com.mashup.latte.ui.record.data.alcohol

import androidx.annotation.DrawableRes
import com.mashup.latte.R

/**
 * Created by Namget on 2020.02.05.
 */
data class Liquor(
    @DrawableRes override val imageResource: Int = R.drawable.img_spirits,
    override val typeList: MutableList<String> = arrayListOf(
        "선택안함", "보드카", "럼", "데킬라", "칵테일"
    ),
    override val name: String = "양주",
    override val cup: Int = 0,
    override val bottle: Int = 0
) : Alcohol