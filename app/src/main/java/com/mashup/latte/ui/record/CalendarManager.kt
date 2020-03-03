package com.mashup.latte.ui.record

import com.mashup.latte.R
import com.mashup.latte.ext.e
import com.mashup.latte.ui.record.data.CalendarRow
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Namget on 2020.03.02.
 */
class CalendarManager {
    private val calendarRows = mutableListOf<CalendarRow>()
    private val firstDate = GregorianCalendar(Locale.KOREA).also {
        it.set(Calendar.DATE, 1)
        e("CalendarManager", "YEAR ${it.get(Calendar.YEAR)}")
        e("CalendarManager", "MONTH ${it.get(Calendar.MONTH)}")
        e("CalendarManager", "DATE ${it.get(Calendar.DATE)}")
        e("CalendarManager", "DAY_OF_WEEK ${it.get(Calendar.DAY_OF_WEEK)}")
    }
    private val format = SimpleDateFormat("yyyy.MM", Locale.KOREA)
    val nowDate get() = format.format(Date(firstDate.timeInMillis))


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
                    when {
                        isSunday(i) -> {
                            R.color.red
                        }
                        isSaturday(i) -> {
                            R.color.blue
                        }
                        else -> {
                            R.color.black
                        }
                    }
                )
            )
        }
    }

    private val sundayCriteria: IntArray = intArrayOf(1, 7, 6, 5, 4, 3, 2)
    private val saturdayCriteria: IntArray = intArrayOf(0, 6, 5, 4, 3, 2, 1)
    private fun isSunday(day: Int): Boolean = (day % 7 == sundayCriteria[getBalnkCount()])
    private fun isSaturday(day: Int): Boolean = (day % 7 == saturdayCriteria[getBalnkCount()])

    private fun getBalnkCount(): Int = firstDate.get(Calendar.DAY_OF_WEEK) - 1

    private fun clear() {
        calendarRows.clear()
    }
}
