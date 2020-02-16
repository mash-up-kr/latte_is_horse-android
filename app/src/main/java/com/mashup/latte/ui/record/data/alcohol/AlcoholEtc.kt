package com.mashup.latte.ui.record.data.alcohol

import androidx.annotation.DrawableRes
import com.mashup.latte.R
import com.mashup.latte.ui.record.data.alcohol.Alcohol

/**
 * Created by Namget on 2020.02.05.
 */
data class AlcoholEtc(
    @DrawableRes override val imageResource: Int = R.drawable.img_liguid_etc,
    override val typeList: MutableList<String> = arrayListOf(),
    override val name: String = "기타",
    override val cup: Int = 0,
    override val bottle: Int = 0
) : Alcohol