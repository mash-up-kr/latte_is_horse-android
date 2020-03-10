package com.mashup.latte.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mashup.latte.data.datasource.local.DateConverter
import com.mashup.latte.data.datasource.local.ListConverter
import com.mashup.latte.data.datasource.local.dao.AppDao
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary

/**
 * Created by Namget on 2020.02.15.
 */
@Database(entities = [AlcoholDiary::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}