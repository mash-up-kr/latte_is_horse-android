package com.mashup.latte.data.repository

import com.mashup.latte.data.datasource.local.ApiLocalDataSource
import com.mashup.latte.data.datasource.remote.ApiRemoteDataSource

/**
 * Created by Namget on 2020.02.15.
 */
class ApiRepositoryImpl(private val apiLocalDataSource: ApiLocalDataSource,
                        private val apiRemoteDataSource: ApiRemoteDataSource
) : ApiRepository{

}