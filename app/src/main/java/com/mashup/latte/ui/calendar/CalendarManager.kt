package com.mashup.latte.ui.calendar

import com.mashup.latte.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Arim on 2020.03.24.
 */
class CalendarManager {
    private val calendarRows = mutableListOf<CalendarRow>()
    private val firstDate = GregorianCalendar(Locale.KOREA).also {
        it.set(Calendar.DATE, 1)
    }
    private val formatForCalendarSelect = SimpleDateFormat("yyyy.MM", Locale.KOREA)
    val nowDate: String get() = formatForCalendarSelect.format(Date(firstDate.timeInMillis))


    private val selectdDate = GregorianCalendar(Locale.KOREA)
    private val formatForResult = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    var selectedDate: String
        set(value) {
            selectdDate.set(Calendar.YEAR, firstDate.get(Calendar.YEAR))
            selectdDate.set(Calendar.MONTH, firstDate.get(Calendar.MONTH))
            selectdDate.set(Calendar.DATE, value.toInt())
        }
        get() = formatForResult.format(Date(selectdDate.timeInMillis))


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
        setBlankDateList()
        setDateNumberList()
        return calendarRows
    }

    private fun setBlankDateList() {
        for (i in 0 until getBalnkCount()) {
            calendarRows.add(CalendarRow(isSelectable = false))
        }
    }

    private fun setDateNumberList() {
        val dayOfMonth = firstDate.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..dayOfMonth) {
            calendarRows.add(
                CalendarRow(
                    i.toString(),
                    String.format("${nowDate}.%02d", i),
                    textColor =
                    when {
                        isSunday(i) -> {
                            R.color.red
                        }
                        else -> {
                            R.color.black
                        }
                    },
                    dot = true,
                    drunkDot = R.drawable.img_drunken_guide_dot_level1
                )
            )
        }
    }

    private val sundayCriteria: IntArray = intArrayOf(1, 7, 6, 5, 4, 3, 2)
    //private val saturdayCriteria: IntArray = intArrayOf(0, 6, 5, 4, 3, 2, 1)
    private fun isSunday(day: Int): Boolean = (day % 7 == sundayCriteria[getBalnkCount()])
    //private fun isSaturday(day: Int): Boolean = (day % 7 == saturdayCriteria[getBalnkCount()])

    private fun getBalnkCount(): Int = firstDate.get(Calendar.DAY_OF_WEEK) - 1

    private fun clear() {
        calendarRows.clear()
    }
}
