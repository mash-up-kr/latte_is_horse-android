package com.mashup.latte.ui.record

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.mashup.latte.R
import com.mashup.latte.ui.record.adapter.CalendarAdapter
import kotlinx.android.synthetic.main.dialog_calendar.*

/**
 * Created by Namget on 2020.03.02.
 */
class RecordCalendarDialog(context: Context, cancelCallback: (DialogInterface) -> Unit) :
    Dialog(context, false, cancelCallback) {

    private val calendarAdapter: CalendarAdapter by lazy {
        CalendarAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.dialog_calendar)
        init()
        initListener()
    }

    private fun init() {
        recyclerCalendar.apply {
            setHasFixedSize(true)
            adapter = calendarAdapter
        }
    }

    private fun initListener() {
        imgCalendarPrevArrow.setOnClickListener {
            calendarAdapter.prevArrow()
        }
        imgCalendarNextArrow.setOnClickListener {
            calendarAdapter.nextArrow()
        }
        //TODO 날짜 지정 및 날짜 변경
        txtCalendarDate.text = "2020.03"

    }

}