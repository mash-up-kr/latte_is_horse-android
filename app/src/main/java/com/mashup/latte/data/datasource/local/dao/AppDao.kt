package com.mashup.latte.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query

/**
 * Created by Namget on 2020.02.15.
 */
@Dao
interface AppDao{

    @Query("select * from alcohol")
    fun insertAlcoholDiray()
}