package com.mashup.latte.module

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.mashup.latte.BuildConfig
import com.mashup.latte.data.datasource.AppDatabase
import com.mashup.latte.data.datasource.local.ApiLocalDataSource
import com.mashup.latte.data.datasource.remote.ApiRemoteDataSource
import com.mashup.latte.data.datasource.remote.ApiService
import io.reactivex.schedulers.Schedulers
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
private const val LOGIN_BASEURL = "https://naver.com"
private const val TIMEOUT: Long = 10L

val remoteModule = module {
    single(named("headerInterceptor")) {
        Interceptor {
            val original = it.request()
            val request = original.newBuilder()
                .method(original.method(), original.body())
                .build()
            it.proceed(request)
        }
    }

    single(named("httpLoggingInterceptor")) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        GsonBuilder().setLenient().create()
    }

    single {
        OkHttpClient.Builder().addInterceptor(get(named("headerInterceptor")))
            .addInterceptor(get(named("httpLoggingInterceptor")))
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    //apiService naming을 통해 여러 BaseUrl을 가진 apiService 생성
    //동작의 경우
    single(named("loginApi")) {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .baseUrl(LOGIN_BASEURL)
            .build()
    }

    single(named("ApiService")) {
        get<Retrofit>(named("loginApi")).create(ApiService::class.java)
    }
    single {
        ApiRemoteDataSource(get(named("ApiService")))
    }
}


val localModule = module {
    //DB
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "drink.db").build()
    }

    single {
        ApiLocalDataSource(get<AppDatabase>().getDao())
    }
}
