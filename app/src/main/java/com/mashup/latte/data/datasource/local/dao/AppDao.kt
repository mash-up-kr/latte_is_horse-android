package com.mashup.latte.data.datasource.local.dao

import androidx.room.*
import com.mashup.latte.data.datasource.local.ListConverter
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary

/**
 * Created by Namget on 2020.02.15.
 */
@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @TypeConverters(ListConverter::class)
    fun insertAlcoholDiary(alcoholDiary: AlcoholDiary)
}