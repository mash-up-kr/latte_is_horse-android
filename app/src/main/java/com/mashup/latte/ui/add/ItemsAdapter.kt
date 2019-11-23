package com.mashup.latte.ui.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R


class ItemsAdapter(
        private val ct: Context,
        private val itemList: List<Item>,
        private val listener: ItemsAdapterListener
) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>(), Filterable {

    var itemListFiltered: List<Item> = ArrayList()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView

        init {
            name = view.findViewById(R.id.tv_name)

            view.setOnClickListener {
                // send selected contact in callback
                listener.onItemSelected(itemListFiltered.get(adapterPosition))
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemListFiltered.get(position)
        holder.name.text = item.name
    }


    override fun getItemCount(): Int {
        return itemListFiltered.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    itemListFiltered = itemList
                } else {
                    val filteredList: MutableList<Item> = ArrayList()
                    for (row in itemList) { // name match condition. this might differ depending on your requirement
// here we are looking for name or phone number match
                        if (row.name.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    itemListFiltered = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = itemListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                itemListFiltered = filterResults.values as ArrayList<Item>
                notifyDataSetChanged()
            }
        }
    }

    interface ItemsAdapterListener {
        fun onItemSelected(item: Item?)
    }
}