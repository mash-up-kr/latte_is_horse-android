package com.mashup.latte.module

import androidx.room.Room
import com.mashup.latte.data.datasource.AppDatabase
import com.mashup.latte.data.datasource.local.ApiLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Namget on 2020.02.15.
 */

val localModule = module {
    //DB
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "drink.db").build()
    }

    single {
        ApiLocalDataSource(get<AppDatabase>().appDao())
    }
}
