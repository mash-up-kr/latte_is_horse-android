package com.mashup.latte.module

import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.data.repository.ApiRepositoryImpl
import com.mashup.latte.pref.UserPref
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Namget on 2020.02.26.
 */
val sharedModule = module {
    single{
        UserPref(androidApplication())
    }
}