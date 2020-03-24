package com.mashup.latte.data.datasource.local.dao

import androidx.room.*
import com.mashup.latte.data.datasource.local.DateConverter
import com.mashup.latte.data.datasource.local.ListConverter
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import io.reactivex.Single
import java.util.*

/**
 * Created by Namget on 2020.02.15.
 */
@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @TypeConverters(ListConverter::class)
    fun insertAlcoholDiary(alcoholDiary: AlcoholDiary)

    @Query("SELECT * FROM alcoholDiary WHERE timestamp BETWEEN :from AND :to ORDER BY timeStamp DESC , id DESC")
    fun selectAlcoholDiary(from: Date = Date(), to: Date = Date()): Single<List<AlcoholDiary>>

    @Query("SELECT * FROM alcoholDiary WHERE id = :id")
    fun selectAlcoholDiaryById(id: Long): Single<AlcoholDiary>

    @Query("SELECT * FROM alcoholDiary")
    fun selectAlcoholAll(): Single<List<AlcoholDiary>>

    @Query("SELECT * FROM alcoholDiary WHERE timestamp BETWEEN :from AND :to ORDER BY timeStamp DESC ,id DESC")
    fun selectAlcoholMonth(from: Date, to: Date): Single<List<AlcoholDiary>>
}