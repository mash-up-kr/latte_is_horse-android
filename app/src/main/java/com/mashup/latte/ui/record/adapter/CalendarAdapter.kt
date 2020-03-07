package com.mashup.latte.ui.record.adapter

import android.os.Build
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
class CalendarAdapter(private val calendarManager: CalendarManager) :
    RecyclerView.Adapter<CalendarAdapter.CalenderViewHolder>() {
    private val calendarRows: MutableList<CalendarRow> = mutableListOf()
    private val selectedDate get() = calendarManager.selectedDate

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
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                calendarText.setTextColor(view.context.resources.getColor(data.textColor, null))
            } else {
                calendarText.setTextColor(view.context.resources.getColor(data.textColor))
            }

            if (data.isSelected) {
                calendarText.background =
                    view.context.resources.getDrawable(R.drawable.shape_corner_circle_s, null)
            }else{
                calendarText.background = null
            }

        }
    }
}