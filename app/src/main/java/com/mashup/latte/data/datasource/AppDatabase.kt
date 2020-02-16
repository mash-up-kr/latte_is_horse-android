package com.mashup.latte.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mashup.latte.data.datasource.local.dao.AppDao
import com.mashup.latte.data.datasource.local.entity.Alcohol

/**
 * Created by Namget on 2020.02.15.
 */
@Database(entities = [Alcohol::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): AppDao
}