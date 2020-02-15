package com.mashup.latte.module

import com.mashup.latte.data.datasource.local.ApiLocalDataSource
import com.mashup.latte.data.datasource.remote.ApiRemoteDataSource
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.data.repository.ApiRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Namget on 2020.02.15.
 */
val localModule = module {
    single {
        ApiRepositoryImpl(get(), get()) as ApiRepository
    }

    single {
        ApiLocalDataSource()
    }

    single {
//        ApiRemoteDataSource(get())
    }
}