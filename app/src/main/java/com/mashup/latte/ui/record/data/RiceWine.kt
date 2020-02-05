package com.mashup.latte.ui.record.data

import androidx.annotation.DrawableRes
import com.mashup.latte.R

/**
 * Created by Namget on 2020.02.05.
 */
data class RiceWine(
    @DrawableRes override val imageResource: Int = R.drawable.selector_alcohol_soju,
    override val typeList: MutableList<String> = arrayListOf(
        "처음처럼", "참이슬", "진로"
    ),
    override val name :String = "막걸리",
    override val cup: Int = 0,
    override val bottle: Int = 0
) : Alcohol