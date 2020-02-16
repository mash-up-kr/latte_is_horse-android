package com.mashup.latte.data.datasource.local

import com.mashup.latte.data.datasource.local.dao.AppDao
import com.mashup.latte.data.repository.ApiRepository

/**
 * Created by Namget on 2020.02.15.
 */
class ApiLocalDataSource(val appDao: AppDao) : ApiRepository {


}