package com.mashup.latte.ui.record.data.alcohol

import androidx.annotation.DrawableRes

/**
 * Created by Namget on 2020.02.05.
 */
interface Alcohol {
    val imageResource: Int
    val name: String
    val typeList: MutableList<String>
    val cup: Int
    val bottle: Int
}