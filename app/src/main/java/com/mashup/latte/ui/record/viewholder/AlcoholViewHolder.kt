package com.mashup.latte.ui.record.viewholder

import android.view.View
import android.widget.*
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.base.BaseViewHolder
import com.mashup.latte.ui.record.data.alcohol.Alcohol

/**
 * Created by Namget on 2020.01.23.
 */
class AlcoholViewHolder(view: View) : BaseViewHolder<Alcohol>(view) {
    private val alcoholImg: ImageView = view.findViewById(R.id.imgItemAlcohol)
    val close: ImageView = view.findViewById(R.id.imgAlcoholClose)
    private val alcoholTitle: TextView = view.findViewById(R.id.txtItemAlcohol)
    private val alcoholBottle: TextView = view.findViewById(R.id.edtAlcoholBottle)
    private val alcoholCup: TextView = view.findViewById(R.id.edtAlcoholCup)
    private val alcoholSpinner: Spinner = view.findViewById(R.id.spinnerAlcoholType)
    private val alcoholEtcLinearlayout: LinearLayout =
        view.findViewById(R.id.linearLayoutAlcoholType)
    private val alcoholEtcType: EditText = view.findViewById(R.id.editTextAlcoholType)

    override fun bind(data: Alcohol) {
        with(data) {
            alcoholImg.load(imageResource)
            alcoholTitle.text = name
            if (typeList.isNullOrEmpty()) {
                alcoholSpinner.visibility = View.GONE
                alcoholEtcLinearlayout.visibility = View.VISIBLE
            }

            val adapter = ArrayAdapter<String>(
                alcoholSpinner.context,
                R.layout.item_spinner_alcohol,
                typeList
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            alcoholSpinner.adapter = adapter

        }
    }
}