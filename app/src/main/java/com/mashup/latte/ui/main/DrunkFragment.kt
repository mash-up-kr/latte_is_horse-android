package com.mashup.latte.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.data.DrunkCaffeine
import com.mashup.latte.ui.main.adapter.DrunkRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_drunk.*
import kotlinx.android.synthetic.main.item_drunk_caffeine.view.drunkCaffeineImg
import kotlinx.android.synthetic.main.item_drunk_caffeine.view.drunkNameTxt
import kotlinx.android.synthetic.main.item_drunk_fill.view.*

/**
 * Created by Namget on 2019.11.23.
 */
class DrunkFragment : Fragment() {

    companion object {
        lateinit var drunkFragment: DrunkFragment

        fun newInstance(): DrunkFragment {
            synchronized(DrunkFragment::class) {
                drunkFragment = DrunkFragment()
                val args = Bundle()
                drunkFragment.arguments = args
                return drunkFragment
            }
        }
    }

    private val drunkRecyclerViewAdapter: DrunkRecyclerViewAdapter by lazy {
        DrunkRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drunk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        requestTodayDrunkList()
    }

    private fun initRecyclerView() {
        caffeineRecyclerView.apply {
            adapter = drunkRecyclerViewAdapter

        }
    }

    private fun requestTodayDrunkList() {
        showData()
    }

    private fun showData() {

        val drunkCaffeine = DrunkCaffeine("", "테스트", 114)
        val list: MutableList<DrunkCaffeine> = arrayListOf()
        list.add(drunkCaffeine)
        list.add(drunkCaffeine)
        list.add(drunkCaffeine)
        makeCupView(list)
        makeDrunkCardView(list)
    }

    private fun makeCupView(list: List<DrunkCaffeine>) {
        dayCaffeineRecTxt.text = String.format(getString(R.string.main_recommend_caffeine), 220)

        for ((index, data) in list.withIndex()) {
            val view = layoutInflater.inflate(R.layout.item_drunk_fill, null, false)
            view.drunkCaffeineImg.load(R.mipmap.ic_launcher)
            view.drunkNameTxt.text = (data.name + index)
            val color = if (android.os.Build.VERSION.SDK_INT >= 23)
                resources.getColor(R.color.colorPrimary, null)
            else resources.getColor(R.color.colorPrimary)

            view.drunkFillLayout.setBackgroundColor(color)
            cupLayout.addView(view)
        }

    }

    private fun makeDrunkCardView(list: List<DrunkCaffeine>) {
        drunkRecyclerViewAdapter.setList(list)
    }


}