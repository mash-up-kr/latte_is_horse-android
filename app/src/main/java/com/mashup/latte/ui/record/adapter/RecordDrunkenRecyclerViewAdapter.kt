package com.mashup.latte.ui.record.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ui.record.data.DrunkenAfter
import com.mashup.latte.ui.record.viewholder.DrunkenAfterViewHolder

/**
 * Created by Namget on 2020.01.23.
 */
class RecordDrunkenRecyclerViewAdapter : RecyclerView.Adapter<DrunkenAfterViewHolder>() {
    private val drunkenAfters: MutableList<DrunkenAfter> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrunkenAfterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_record_drunken, parent, false)
        return DrunkenAfterViewHolder(view)
    }

    override fun getItemCount(): Int = drunkenAfters.size

    override fun onBindViewHolder(holder: DrunkenAfterViewHolder, position: Int) {
        holder.bind(drunkenAfters[position])
    }
}