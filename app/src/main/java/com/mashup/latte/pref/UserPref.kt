package com.mashup.latte.pref

import android.content.Context
import com.mashup.latte.ext.getBooleanPreference
import com.mashup.latte.ext.getStringPreference
import com.mashup.latte.ext.setBooleanPreference
import com.mashup.latte.ext.setStringPreference
import kotlin.properties.Delegates

class UserPref(val context: Context) {


    private var isSNSLogined: Boolean by Delegates.observable(
        context.getBooleanPreference(
            SNS_LOGINED, false
        )
    ) { _, _, newValue ->
        context.setBooleanPreference(SNS_LOGINED, newValue)
        isFirst = false
    }

    private var isFirst: Boolean by Delegates.observable(
        context.getBooleanPreference(IS_LOGINED, true)
    ) { _, _, newValue ->
        context.setBooleanPreference(IS_LOGINED, value = newValue)
    }

    private var token: String by Delegates.observable(context.getStringPreference(TOKEN)) { _, oldValue, newValue ->
        if (oldValue != newValue)
            context.setStringPreference(TOKEN, newValue)
    }

    fun setAceessToken(newToken: String) {
        token = newToken
    }

    fun getAccessToken(): String = token

    fun setSNSLogin(isLogin: Boolean) {
        isSNSLogined = isLogin
    }

    fun isSNSLogin(): Boolean =
        isSNSLogined

    fun isFirstLogined(): Boolean = isFirst
    fun setFirstLogin(firstLogined: Boolean) {
        isFirst = firstLogined
    }

    companion object {
        const val SNS_LOGINED = "sns_logined"
        const val TOKEN = "token"
        const val IS_LOGINED = "is_login"
    }
}