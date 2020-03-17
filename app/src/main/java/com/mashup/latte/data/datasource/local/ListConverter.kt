package com.mashup.latte.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mashup.latte.ext.fromJson
import com.mashup.latte.ui.record.data.result.DrunkenAmount
import java.sql.Date

/**
 * Created by Namget on 2020.02.23.
 */
class ListConverter {


    @TypeConverter
    fun listAmountToJsonArray(list: List<DrunkenAmount>): String =
        Gson().toJson(list)

    @TypeConverter
    fun arrayToListAmout(string: String): List<DrunkenAmount> =
        Gson().fromJson<List<DrunkenAmount>>(string)

    @TypeConverter
    fun arrayAmountToSring(list: Array<String>): String =
        Gson().toJson(list)

    @TypeConverter
    fun stringToArrayAmount(string: String): Array<String> =
        Gson().fromJson<Array<String>>(string)


}