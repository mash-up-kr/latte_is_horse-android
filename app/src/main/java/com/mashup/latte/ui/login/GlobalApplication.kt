package com.mashup.latte.ui.login

import com.kakao.auth.KakaoSDK
import android.app.Application


class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        // Kakao Sdk 초기화
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    companion object {
        private var instance: GlobalApplication? = null
        fun getGlobalApplicationContext(): GlobalApplication {
            checkNotNull(instance) { "This Application does not inherit com.kakao.GlobalApplication" }
            return instance!!
        }
    }
}