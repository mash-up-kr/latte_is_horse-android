package com.mashup.latte.ext

import android.content.Context
import androidx.core.content.edit

fun Context.getStringPreference(key: String): String {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)?.getString(key, "")
        ?: ""
}

fun Context.setStringPreference(key: String, value: String) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit(false) {
        putString(key, value)
    }
}

fun Context.getIntegerPreference(key: String): Int {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).getInt(key, 0)
}

fun Context.setIntegerPreference(key: String, value: Int) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit(false) {
        putInt(key, value)
    }
}

fun Context.getBooleanPreference(key: String, default: Boolean = false): Boolean {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)?.getBoolean(
        key,
        default
    ) ?: false
}

fun Context.setBooleanPreference(key: String, value: Boolean) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit(false) {
        putBoolean(key, value)
    }
}


fun Context.getLongPreference(key: String): Long {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
        ?.getLong(key, 0L) ?: 0L
}

fun Context.setLongPreference(key: String, value: Long) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit(false) {
        putLong(key, value)
    }
}