package com.mashup.latte.ui.record

import android.location.Criteria
import com.mashup.latte.R
import com.mashup.latte.ui.record.data.CalendarRow
import java.util.*

/**
 * Created by Namget on 2020.03.02.
 */
class CalendarManager {
    private val calendarRows = mutableListOf<CalendarRow>()
    private val firstDate = GregorianCalendar(Locale.KOREA).also {
        it.set(Calendar.DATE, 1)
    }

    fun prevMonth(): List<CalendarRow> {
        firstDate.set(Calendar.MONTH, firstDate.get(Calendar.MONTH) - 1)
        return buildCalendar()
    }

    fun nextMonth(): List<CalendarRow> {
        firstDate.set(Calendar.MONTH, firstDate.get(Calendar.MONTH) + 1)
        return buildCalendar()
    }

    fun buildCalendar(): List<CalendarRow> {
        clear()
        setDateList()
        setBlankDateList()
        setDateNumberList()
        return calendarRows
    }

    private fun setDateList() {
        calendarRows.add(CalendarRow(text = "S", textColor = R.color.red))
        calendarRows.add(CalendarRow(text = "M"))
        calendarRows.add(CalendarRow(text = "T"))
        calendarRows.add(CalendarRow(text = "W"))
        calendarRows.add(CalendarRow(text = "T"))
        calendarRows.add(CalendarRow(text = "F"))
        calendarRows.add(CalendarRow(text = "S", textColor = R.color.blue))
    }

    private fun setBlankDateList() {
        for (i in 0 until getBalnkCount()) {
            calendarRows.add(CalendarRow())
        }
    }

    private fun setDateNumberList() {
        val sunday = 7 - getBalnkCount()
        val dayOfMonth = firstDate.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..dayOfMonth) {
            calendarRows.add(
                CalendarRow(
                    i.toString(),
                    if (isSunday(sunday, dayOfMonth)) {
                        R.color.red
                    } else {
                        R.color.black
                    }
                )
            )
        }
    }

    private fun isSunday(day: Int, criteria: Int): Boolean = (day % criteria == 1)

    private fun getBalnkCount(): Int = firstDate.get(Calendar.DAY_OF_WEEK)

    private fun clear() {
        calendarRows.clear()
    }

}