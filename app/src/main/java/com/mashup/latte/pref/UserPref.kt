package com.mashup.latte.pref

object UserPref {
    private var isLogined = false

    fun setLogin(isLogin: Boolean) {
        isLogined = isLogin
    }

    fun isLogin(): Boolean =
        isLogined
}