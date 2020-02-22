package com.mashup.latte.data.datasource.local.entity

import androidx.room.Entity
import com.mashup.latte.ui.record.data.result.DrunkenAmount

/**
 * Created by Namget on 2020.02.22.
 *
 */
@Entity
data class AlcoholDiary(
    val date: String = "",
    val drunkenStatus: String = "",
    val hanoverStatus: String = "",
    val drunkenAmounts: List<DrunkenAmount> = arrayListOf(),
    val name: String,
    val type: String,
    val cup: String,
    val bottle: String,
    var imagePath: Array<String?> = arrayOfNulls<String>(3)
)