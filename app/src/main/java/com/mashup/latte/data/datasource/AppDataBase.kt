package com.mashup.latte.data.datasource

import androidx.room.RoomDatabase
import com.mashup.latte.data.datasource.local.dao.AppDao

/**
 * Created by Namget on 2020.02.15.
 */
abstract class AppDataBase : RoomDatabase(){
    abstract fun getDao() : AppDao
}