package com.mashup.latte.pref

import android.content.Context
import android.media.session.MediaSession
import com.mashup.latte.ext.getBooleanPreference
import com.mashup.latte.ext.getStringPreference
import com.mashup.latte.ext.setBooleanPreference
import com.mashup.latte.ext.setStringPreference
import kotlin.properties.Delegates

class UserPref(val context: Context) {
    companion object {
        const val SNS_LOGINED = "sns_logined"
        const val TOKEN = "token"
    }

    private var isSNSLogined: Boolean by Delegates.observable(context.getBooleanPreference(
        SNS_LOGINED,false)) { _, oldValue, newValue ->
        if (oldValue != newValue)
            context.setBooleanPreference(SNS_LOGINED, newValue)
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
}