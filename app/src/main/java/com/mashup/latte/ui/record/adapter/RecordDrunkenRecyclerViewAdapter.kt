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
        DrunkenAfter(R.drawable.ic_drunken_mulggyung, "완전멀쩡", true,11),
        DrunkenAfter(R.drawable.ic_drunken_alddalddal, "살짝알딸딸", false,22),
        DrunkenAfter(R.drawable.ic_drunken_eumjugamu, "음주가무", false,33),
        DrunkenAfter(R.drawable.ic_drunken_rainbow_vomit, "무지개토", false,44),
        DrunkenAfter(R.drawable.ic_drunken_crying, "눈물줄줄", false,55),
        DrunkenAfter(R.drawable.ic_drunken_skinship, "스킨십귀신", false,66),
        DrunkenAfter(R.drawable.ic_drunken_blackout, "필름끊김", false,77),
        DrunkenAfter(R.drawable.ic_drunken_honey_sleep, "꿀잠쿨쿨", false,88),
        DrunkenAfter(R.drawable.ic_drunken_go_home_fairy, "귀가요정", false,99)
    )
    private var beforeSelectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrunkenAfterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_record_drunken, parent, false)

        return DrunkenAfterViewHolder(view) { position ->
            setSelected(position)
        }
    }

    fun getSelectedText(): String {
        for (drunkenAfter in drunkenAfters) {
            if (drunkenAfter.isChecked) {
                return drunkenAfter.text
            }
        }
        return ""
    }



    override fun getItemId(position: Int): Long {
        return drunkenAfters[position].id
    }

    private fun setSelected(selectedPosition: Int) {
        //선택되지 않은 이미지
        if (selectedPosition == beforeSelectedPosition) {
            return
        }

        drunkenAfters[selectedPosition].isChecked = true
        drunkenAfters[beforeSelectedPosition].isChecked = false
        notifyDataSetChanged()

        beforeSelectedPosition = selectedPosition
    }

    override fun getItemCount(): Int = drunkenAfters.size


    override fun onBindViewHolder(holder: DrunkenAfterViewHolder, position: Int) {
        holder.bind(drunkenAfters[position])
    }
}