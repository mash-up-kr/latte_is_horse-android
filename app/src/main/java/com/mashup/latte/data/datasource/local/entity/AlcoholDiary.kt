package com.mashup.latte.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mashup.latte.data.datasource.local.ListConverter
import com.mashup.latte.data.dto.request.AlcoholRecord
import com.mashup.latte.data.dto.request.AlcoholRequest
import com.mashup.latte.ui.record.data.result.DrunkenAmount

/**
 * Created by Namget on 2020.02.22.
 *
 */
@Entity(tableName = "alcoholDiary")
data class AlcoholDiary(
    @PrimaryKey
    val id: Long? = null,
    val date: String = "",
    val drunkenStatus: String = "",
    val hanoverStatus: String = "",
    val drunkenAmounts: List<DrunkenAmount> = arrayListOf(),
    val review: String = "",
    val drunkenActionType: String = "",
    val imagePath: Array<String?>? = arrayOfNulls<String>(3)
) {
    fun toAlcoholRequest(): AlcoholRequest {
        return AlcoholRequest(
            review = review,
            date = date,
            drunkenLevel = drunkenStatus,
            hangoverLevel = hanoverStatus,
            actionType = drunkenActionType,
            alcoholRecords = drunkenAmountsToAlcoholRecords(drunkenAmounts),
            actionTypeImg = "",
            imageSet = arrayListOf()
        )
    }

    private fun drunkenAmountsToAlcoholRecords(drunkenAmounts: List<DrunkenAmount>): List<AlcoholRecord> {
        val result: MutableList<AlcoholRecord> = mutableListOf()
        for (drunkenAmount in drunkenAmounts) {
            result.add(
                AlcoholRecord(
                    bottles = drunkenAmount.bottle.toDouble(),
                    glasses = drunkenAmount.cup.toDouble(),
                    alcoholType = drunkenAmount.type,
                    alcoholName = drunkenAmount.name
                )
            )
        }
        return result
    }

}