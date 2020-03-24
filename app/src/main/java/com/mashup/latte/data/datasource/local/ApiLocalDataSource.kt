package com.mashup.latte.data.datasource.local

import com.mashup.latte.data.datasource.local.dao.AppDao
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.dto.request.TokenRequest
import com.mashup.latte.data.dto.response.TokenResponse
import com.mashup.latte.data.repository.ApiRepository
import io.reactivex.Single
import java.util.*

/**
 * Created by Namget on 2020.02.15.
 */
class ApiLocalDataSource(private val appDao: AppDao) : ApiRepository {

    override fun insertAlcoholDiary(alcoholDiary: AlcoholDiary) =
        appDao.insertAlcoholDiary(alcoholDiary)

    override fun getDiariesByDate(from: Date, to: Date): Single<List<AlcoholDiary>> =
        appDao.selectAlcoholDiary(from, to)


    override fun getLoginToken(tokenRequest: TokenRequest): Single<TokenResponse> {
        throw IllegalAccessException("local api not supported")
    }

    override fun getDiaryById(id: Long): Single<AlcoholDiary> =
        appDao.selectAlcoholDiaryById(id)

    override fun getDiariesAll(): Single<List<AlcoholDiary>> =
        appDao.selectAlcoholAll()


    override fun getDiariesThisMonth(monthDate: Date): Single<List<AlcoholDiary>> {
        val calendarStart = Calendar.getInstance()
        calendarStart.time = monthDate
        calendarStart.set(Calendar.DATE, 1)
        calendarStart.set(Calendar.AM, calendarStart.getActualMaximum(Calendar.AM))


        val calendarEnd = Calendar.getInstance()
        calendarEnd.time = monthDate
        calendarEnd.set(Calendar.DATE, calendarEnd.getActualMaximum(Calendar.DATE))
        calendarEnd.set(Calendar.PM, calendarEnd.getActualMaximum(Calendar.PM))

        return appDao.selectAlcoholMonth(calendarStart.time,calendarEnd.time)
    }
}