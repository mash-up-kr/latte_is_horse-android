package com.mashup.latte.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.data.DrunkCaffeine
import kotlinx.android.synthetic.main.item_drunk_caffeine.view.*
import java.util.zip.Inflater

/**
 * Created by Namget on 2019.11.24.
 */
class DrunkRecyclerViewAdapter() : RecyclerView.Adapter<DrunkRecyclerViewAdapter.MyViewHolder>() {
    private val itemList: MutableList<DrunkCaffeine> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_drunk_caffeine, parent, false)
        return MyViewHolder(view)
    }

    fun setList(list: List<DrunkCaffeine>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val drunkCaffeineImg: ImageView = view.findViewById(R.id.drunkCaffeineImg)
        private val drunkNameTxt: TextView = view.findViewById(R.id.drunkNameTxt)
        private val drunkScoreTxt: TextView = view.findViewById(R.id.drunkScoreTxt)


        fun bind(item: DrunkCaffeine) {
            drunkCaffeineImg.load(R.mipmap.ic_launcher)
            drunkNameTxt.text = item.name
            drunkScoreTxt.text = item.score.toString()
        }
    }

}