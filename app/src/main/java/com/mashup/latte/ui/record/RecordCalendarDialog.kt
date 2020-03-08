package com.mashup.latte.ui.record

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.mashup.latte.R
import com.mashup.latte.ui.record.adapter.CalendarAdapter
import kotlinx.android.synthetic.main.dialog_calendar.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Namget on 2020.03.02.
 */
class RecordCalendarDialog(
    context: Context,
    cancelCallback: (DialogInterface) -> Unit,
    private val callback: (String) -> Unit
) :
    Dialog(context, true, cancelCallback), KoinComponent {

    private val calendarManager: CalendarManager by inject()
    private val calendarAdapter: CalendarAdapter by lazy {
        CalendarAdapter(calendarManager)
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
            txtCalendarDate.text = calendarManager.nowDate
        }
        imgCalendarNextArrow.setOnClickListener {
            calendarAdapter.nextArrow()
            txtCalendarDate.text = calendarManager.nowDate
        }
        txtCalendarDate.text = calendarManager.nowDate

        txtCalendarCancel.setOnClickListener {
            dismiss()
        }
        txtCalendarOk.setOnClickListener {
            callback(calendarManager.selectedDate)
            dismiss()
        }

    }
}