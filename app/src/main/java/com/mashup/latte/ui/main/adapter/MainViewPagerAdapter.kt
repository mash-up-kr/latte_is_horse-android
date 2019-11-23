package com.mashup.latte.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by Namget on 2019.11.23.
 */
class MainViewPagerAdapter(
    fm: androidx.fragment.app.FragmentManager,
    val fragmentList: ArrayList<androidx.fragment.app.Fragment>
) : androidx.fragment.app.FragmentPagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment = fragmentList[position]
    override fun getCount(): Int = fragmentList.size
}