package com.mashup.latte.ui.main_detail

import androidx.fragment.app.Fragment

class MainDetailViewPagerAdapter(
    fm: androidx.fragment.app.FragmentManager,
    private val fragmentList: ArrayList<Fragment>
) : androidx.fragment.app.FragmentStatePagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment = fragmentList[position]
    override fun getCount(): Int = fragmentList.size
    override fun getPageWidth(position: Int): Float {
        return pageWidth
    }

    companion object {
        const val pageWidth = 0.8f
    }
}