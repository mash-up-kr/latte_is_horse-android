package com.mashup.latte.ui

import com.kakao.auth.KakaoSDK
import android.app.Application
import com.mashup.latte.BuildConfig
import com.mashup.latte.module.localModule
import com.mashup.latte.module.repositoryModule
import com.mashup.latte.ui.login.KakaoSDKAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger


class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        // Kakao Sdk 초기화
        KakaoSDK.init(KakaoSDKAdapter())
        setupKoin()
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }


    fun setupKoin() {
        startKoin {
            logger(if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger())
            androidContext(this@GlobalApplication)
            modules(listOf(localModule, repositoryModule))
        }
    }


    companion object {
        private var instance: GlobalApplication? = null
        fun getGlobalApplicationContext(): GlobalApplication {
            checkNotNull(instance) { "This Application does not inherit com.kakao.GlobalApplication" }
            return instance!!
        }
    }
}