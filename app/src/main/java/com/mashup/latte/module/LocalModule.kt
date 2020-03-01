package com.mashup.latte.module

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.mashup.latte.BuildConfig
import com.mashup.latte.data.datasource.AppDatabase
import com.mashup.latte.data.datasource.local.ApiLocalDataSource
import com.mashup.latte.data.datasource.remote.ApiRemoteDataSource
import com.mashup.latte.data.datasource.remote.ApiService
import com.mashup.latte.data.datasource.remote.KaKaoApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Namget on 2020.02.15.
 */

val localModule = module {
    //DB
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "drink.db").build()
    }

    single {
        ApiLocalDataSource(get<AppDatabase>().getDao())
    }
}
