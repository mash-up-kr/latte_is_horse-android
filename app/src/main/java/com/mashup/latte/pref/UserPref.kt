package com.mashup.latte.pref

object UserPref {
    private var isSNSLogined = false

    fun setSNSLogin(isLogin: Boolean) {
        isSNSLogined = isLogin
    }

    fun isSNSLogin(): Boolean =
        isSNSLogined
}