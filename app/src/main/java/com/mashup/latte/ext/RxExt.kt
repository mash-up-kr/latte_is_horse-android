package com.mashup.latte.ext

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Namget on 2020.02.27.
 */

fun <T> Single<T>.withScheduler(): Single<T> = subscribeOn(Schedulers.io()).observeOn(
    AndroidSchedulers.mainThread())