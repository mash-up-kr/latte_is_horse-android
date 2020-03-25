package com.mashup.latte.ui.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mashup.latte.R
import kotlinx.android.synthetic.main.activity_calendar.*

/**
 * Created by Arim on 2020.03.24.
 */
class CalendarActivity : AppCompatActivity() {

    private val calendarManager = CalendarManager()
    private val calendarAdapter: CalendarAdapter by lazy {
        CalendarAdapter(calendarManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        init()
        initListener()
        showDataLayout.visibility = View.INVISIBLE
        dotInfo.visibility = View.VISIBLE
    }

    private fun init() {
        initEvent()
        recyclerCalendar.apply {
            setHasFixedSize(true)
            adapter = calendarAdapter
        }
    }

    private fun initEvent() {
        ImgCalendarBack.setOnClickListener{
            finish()
        }
    }

    private fun initListener() {
        calendar_prev_button.setOnClickListener {
            calendarAdapter.prevArrow()
            txtCalendarDate.text = calendarManager.nowDate
        }
        calendar_next_button.setOnClickListener {
            calendarAdapter.nextArrow()
            txtCalendarDate.text = calendarManager.nowDate
        }
        txtCalendarDate.text = calendarManager.nowDate
    }

    fun showDairyData(dot: Boolean){
        if(dot){
            dotInfo.visibility = View.INVISIBLE
            showDataLayout.visibility = View.VISIBLE
        }else{
            showDataLayout.visibility = View.INVISIBLE
            dotInfo.visibility = View.VISIBLE
        }
    }
}
