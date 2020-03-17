package com.mashup.latte.module

import com.google.gson.GsonBuilder
import com.mashup.latte.BuildConfig
import com.mashup.latte.data.datasource.remote.ApiRemoteDataSource
import com.mashup.latte.data.datasource.remote.ApiService
import com.mashup.latte.ext.e
import com.mashup.latte.pref.UserPref
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Namget on 2020.02.27.
 */
private const val BASEURL = "http://ec2-54-180-89-116.ap-northeast-2.compute.amazonaws.com"
private const val KAKAOURL = "https://kauth.kakao.com"
private const val TIMEOUT: Long = 10L
val remoteModule = module {
    single(named("headerInterceptor")) {
        Interceptor {
            val userPref: UserPref by inject()

            val original = it.request()
            val request = original.newBuilder()
                .method(original.method(), original.body())
                .addHeader("Content-Type", "application/json")
            if (userPref.getAccessToken().isNotEmpty()) {
                e("test", "${userPref.getAccessToken()}")
                request.addHeader("Authorization", "Bearer ${userPref.getAccessToken()}")
            }
            it.proceed(request.build())
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
    single(named("diaryApi")) {
        Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

    single(named("KakaoApi")) {
        Retrofit.Builder()
            .baseUrl(KAKAOURL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(get())
            .build()
    }

    single(named("ApiService")) {
        //get<Retrofit>(named("loginApi")).create(ApiService::class.java)
        get<Retrofit>(named("diaryApi")).create(ApiService::class.java)
    }

    single {
        ApiRemoteDataSource(get(named("ApiService")), get(), get())
    }
}