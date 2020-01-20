package com.mashup.latte.ext

import android.app.DatePickerDialog
import android.content.Context
import com.mashup.latte.R
import java.util.*


fun Calendar.showDateDialog(
    context: Context,
    callback: (String) -> Unit,
    mYear: Int = get(Calendar.YEAR),
    mMonth: Int = get(Calendar.MONTH),
    mDate: Int = get(Calendar.DATE)
) {
    val dialog = DatePickerDialog(
        context,
        R.style.datePicker,
        DatePickerDialog.OnDateSetListener { _, year, month, date ->
            val msg = String.format("%d년%02d월%02d일", year, month + 1, date)
            callback(msg)
            context.toastMakeToast(msg)
        }, mYear, mMonth, mDate
    )
    dialog.show()
}