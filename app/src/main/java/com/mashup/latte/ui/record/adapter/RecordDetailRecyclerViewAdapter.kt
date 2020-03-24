package com.mashup.latte.ui.record.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ui.record.data.alcohol.Alcohol
import com.mashup.latte.ui.record.viewholder.AlcoholViewHolder


/**
 * Created by Namget on 2020.01.23.
 */
class RecordDetailRecyclerViewAdapter : RecyclerView.Adapter<AlcoholViewHolder>() {
    private val alcohols: MutableList<Alcohol> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlcoholViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_alcohol, parent, false)
        return AlcoholViewHolder(view)
    }

    fun addAlcohol(alcohol: Alcohol) {
        alcohols.add(alcohol)
        notifyItemInserted(alcohols.size -1)
    }

    fun deleteAlcohol(position: Int) {
        alcohols.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = alcohols.size

    override fun onBindViewHolder(holder: AlcoholViewHolder, position: Int) {
        holder.bind(alcohols[position])

        holder.close.setOnClickListener {
            deleteAlcohol(position)
        }
    }
}

