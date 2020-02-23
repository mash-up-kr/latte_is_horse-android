package com.mashup.latte.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Namget on 2020.02.23.
 */
inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)