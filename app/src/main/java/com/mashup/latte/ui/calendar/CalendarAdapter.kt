package com.mashup.latte.ui.calendar

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_calendar_check_row.view.*

/**
 * Created by Arim on 2020.03.24.
 */
class CalendarAdapter(private val calendarManager: CalendarManager) :
    RecyclerView.Adapter<CalendarAdapter.CalenderViewHolder>() {
    private val calendarRows: MutableList<CalendarRow> = mutableListOf()
    private val selectedDate get() = calendarManager.selectedDate

    private val calendarActivity = CalendarActivity()
    private var dot: Boolean = false

    init {
        replaceData(calendarManager.buildCalendar())
    }

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
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_check_row, parent, false)
        view.setOnClickListener {
            if(view.textCalenderRow.text != "") {
                calendarManager.selectedDate =
                    view.findViewById<TextView>(R.id.textCalenderRow).text.toString()
                notifyDataSetChanged()
                calendarActivity.showDairyData(dot)
            }
        }

        return CalenderViewHolder(view)
    }

    override fun getItemCount(): Int = calendarRows.size

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        holder.bind(calendarRows[position])
    }

    inner class CalenderViewHolder(private val view: View) : BaseViewHolder<CalendarRow>(view) {
        private val calendarText = view.findViewById<TextView>(R.id.textCalenderRow)
        private val calendarDot = view.findViewById<ImageView>(R.id.dotCalendarRow)

        override fun bind(data: CalendarRow) {
            calendarText.text = data.text
            if (Build.VERSION.SDK_INT >= 23) {
                calendarText.setTextColor(view.context.resources.getColor(data.textColor, null))
            } else {
                calendarText.setTextColor(view.context.resources.getColor(data.textColor))
            }

            if (selectedDate == data.fullText) {
                calendarText.background =
                    view.context.resources.getDrawable(R.drawable.shape_corner_circle_s, null)
                calendarText.setTextColor(view.context.resources.getColor(R.color.white))
            } else {
                calendarText.background = null
            }

            dot = data.dot
            if(dot){
                calendarDot.visibility = View.VISIBLE
            } else{
                calendarDot.visibility = View.INVISIBLE
            }

        }
    }
}