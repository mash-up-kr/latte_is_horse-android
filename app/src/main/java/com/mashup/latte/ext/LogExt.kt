package com.mashup.latte.ext

import android.util.Log
import com.mashup.latte.BuildConfig

/**
 * Created by Namget on 2019.10.23.
 */
internal fun e(TAG: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message)
    }
}

internal fun e(TAG: String, message: String, e: Exception?) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message, e)
    }
}

internal fun e(TAG: String, message: String, e: Throwable?) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message, e)
    }
}


internal fun d(TAG: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message)
    }
}

internal fun d(TAG: String, message: String, e: Exception?) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message, e)
    }
}

internal fun d(TAG: String, message: String, e: Throwable?) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message, e)
    }
}

