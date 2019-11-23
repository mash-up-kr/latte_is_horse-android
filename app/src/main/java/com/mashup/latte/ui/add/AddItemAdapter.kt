package com.mashup.latte.ui.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.dataclass.AddListItem
import com.mashup.latte.viewholder.AddItemViewHolder



class AddItemAdapter (private var itemList: ArrayList<AddListItem>): RecyclerView.Adapter<AddItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder{
        val viewHolder: AddItemViewHolder

        val view = LayoutInflater.from(parent.context).inflate(com.mashup.latte.R.layout.add_item, parent, false)
        viewHolder = AddItemViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItemList(itemList: ArrayList<AddListItem>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    fun getItem(position: Int):AddListItem{
        return itemList[position]
    }
}