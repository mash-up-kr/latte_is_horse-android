package com.mashup.latte.ui.add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.latte.R
import com.mashup.latte.dataclass.AddListItem
import kotlinx.android.synthetic.main.activity_add_item.*
import java.util.*

class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val array = ArrayList<AddListItem>()
        array.add(AddListItem("스타벅스", "10mg"))
        array.add(AddListItem("탐앤탐스", "20mg"))
        array.add(AddListItem("스타벅스", "10mg"))
        array.add(AddListItem("탐앤탐스", "20mg"))
        array.add(AddListItem("스타벅스", "10mg"))
        array.add(AddListItem("탐앤탐스", "20mg"))
        array.add(AddListItem("스타벅스", "10mg"))
        array.add(AddListItem("탐앤탐스", "20mg"))
        array.add(AddListItem("스타벅스", "10mg"))
        array.add(AddListItem("탐앤탐스", "20mg"))
        array.add(AddListItem("스타벅스", "10mg"))
        array.add(AddListItem("탐앤탐스", "20mg"))
        array.add(AddListItem("스타벅스", "10mg"))
        array.add(AddListItem("탐앤탐스", "20mg"))

        val adapter = AddItemAdapter(array)
        add_item_recyclerview.adapter = adapter
        add_item_recyclerview.layoutManager = LinearLayoutManager(this)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        date_button.setText("" + year + "." + (month+1) + "." + day)

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        time_button.setText(""+ hour + ":" + minute)

        back_button.setOnClickListener{
            finish()
        }

        ok_button.setOnClickListener{
            /*val nextIntent = Intent(this, ::class.java)
            nextIntent.putExtra("")
            startActivity(nextIntent)*/
        }

        date_button.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                date_button.setText("" + year + "." + (monthOfYear+1) + "." + dayOfMonth)
            }, year, month, day)
            dpd.show()
        }

        time_button.setOnClickListener{
            val tpd = TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,TimePickerDialog.OnTimeSetListener { view, h, m ->
                time_button.setText("" + h + ":" + m)
            },hour,minute,false)
            tpd.show()
        }
    }
}
