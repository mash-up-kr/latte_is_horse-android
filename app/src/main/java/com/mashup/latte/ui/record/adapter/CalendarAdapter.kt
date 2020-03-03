package com.mashup.latte.ui.record.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import com.mashup.latte.ui.record.CalendarManager
import com.mashup.latte.ui.record.data.CalendarRow

/**
 * Created by Namget on 2020.03.02.
 */
class CalendarAdapter(private val calendarManager : CalendarManager) : RecyclerView.Adapter<CalendarAdapter.CalenderViewHolder>() {
    private val calendarRows: MutableList<CalendarRow> = mutableListOf()


    init {
        replaceData(calendarManager.buildCalendar())
    }

    //TODO row 클릭시 click 한 날 동그라미로

    private fun replaceData(list: List<CalendarRow>) {
        calendarRows.clear()
        calendarRows.addAll(list)
        notifyDataSetChanged()
    }

    fun nextArrow() {
        replaceData(calendarManager.nextMonth())
    }

    fun prevArrow() {
        replaceData(calendarManager.prevMonth())
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_row, parent, false)
        return CalenderViewHolder(view)
    }

    override fun getItemCount(): Int = calendarRows.size

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        holder.bind(calendarRows[position])
    }

    class CalenderViewHolder(val view: View) : BaseViewHolder<CalendarRow>(view) {
        private val calendarText = view.findViewById<TextView>(R.id.textCalenderRow)

        override fun bind(data: CalendarRow) {
            calendarText.text = data.text
            calendarText.setTextColor(view.context.resources.getColor(data.textColor, null))
        }
    }
}