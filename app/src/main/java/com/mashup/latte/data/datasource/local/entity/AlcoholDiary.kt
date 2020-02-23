package com.mashup.latte.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mashup.latte.data.datasource.local.ListConverter
import com.mashup.latte.ui.record.data.result.DrunkenAmount

/**
 * Created by Namget on 2020.02.22.
 *
 */
@Entity(tableName = "alcoholDiary")
data class AlcoholDiary(
    @PrimaryKey
    val id : Long? = null,
    val date: String = "",
    val drunkenStatus: String = "",
    val hanoverStatus: String = "",
    val drunkenAmounts: List<DrunkenAmount> = arrayListOf(),
    val content: String = "",
    val drunken: String = "",
    val imagePath: Array<String?>? = arrayOfNulls<String>(3)
)