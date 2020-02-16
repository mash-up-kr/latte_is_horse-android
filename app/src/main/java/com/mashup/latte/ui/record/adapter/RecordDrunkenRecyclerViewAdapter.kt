package com.mashup.latte.ui.record.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mashup.latte.R
import com.mashup.latte.ext.toastMakeToast
import com.mashup.latte.ui.record.data.DrunkenAfter
import com.mashup.latte.ui.record.viewholder.DrunkenAfterViewHolder

/**
 * Created by Namget on 2020.01.23.
 */
class RecordDrunkenRecyclerViewAdapter : RecyclerView.Adapter<DrunkenAfterViewHolder>() {
    private val drunkenAfters: MutableList<DrunkenAfter> = arrayListOf(
        DrunkenAfter(R.drawable.ic_drunken_mulggyung, "완전멀쩡", true),
        DrunkenAfter(R.drawable.ic_drunken_alddalddal, "살짝알딸딸", false),
        DrunkenAfter(R.drawable.ic_drunken_eumjugamu, "음주가무", false),
        DrunkenAfter(R.drawable.ic_drunken_rainbow_vomit, "무지개토", false),
        DrunkenAfter(R.drawable.ic_drunken_crying, "눈물줄줄", false),
        DrunkenAfter(R.drawable.ic_drunken_skinship, "스킨십귀신", false),
        DrunkenAfter(R.drawable.ic_drunken_blackout, "필름끊김", false),
        DrunkenAfter(R.drawable.ic_drunken_honey_sleep, "꿀잠쿨쿨", false),
        DrunkenAfter(R.drawable.ic_drunken_go_home_fairy, "귀가요정", false)
    )
    private var beforeSelectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrunkenAfterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_record_drunken, parent, false)

        return DrunkenAfterViewHolder(view) { position ->
            setSelected(position)
        }
    }

    private fun setSelected(selectedPosition: Int) {
        //선택되지 않은 이미지
        if (selectedPosition == beforeSelectedPosition) {
            return
        }

        drunkenAfters[selectedPosition].isChecked = true
        drunkenAfters[beforeSelectedPosition].isChecked = false
        notifyItemChanged(selectedPosition)
        notifyItemChanged(beforeSelectedPosition)


        beforeSelectedPosition = selectedPosition

    }

    override fun getItemCount(): Int = drunkenAfters.size



    override fun onBindViewHolder(holder: DrunkenAfterViewHolder, position: Int) {
        holder.bind(drunkenAfters[position])
    }
}