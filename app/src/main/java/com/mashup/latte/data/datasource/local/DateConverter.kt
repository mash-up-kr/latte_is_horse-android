package com.mashup.latte.data.datasource.local

import androidx.room.TypeConverter
import java.util.*


/**
 * Created by Namget on 2020.03.10.
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time.toLong()
    }
}