package com.mashup.latte.ui

import android.app.Application
import com.mashup.latte.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

/**
 * Created by Namget on 2019.11.23.
 */
class LatteApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }

//    fun setupKoin(){
//        startKoin {
//            logger(if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger())
//            androidContext(this@LatteApplication)
////            modules()
//        }
//    }

}