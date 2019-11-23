package com.mashup.latte.ui.add

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabPagerAdapter(
        fm: FragmentManager,
        private var tabCount: Int,
        private val searchFragment: SearchFragment,
        private val recentFragment: RecentFragment,
        private val favoriteFragment: FavoriteFragment
) : FragmentStatePagerAdapter(fm, tabCount) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> searchFragment
            1 -> recentFragment
            2 -> favoriteFragment
            else -> Fragment()
        }

    }

    override fun getCount(): Int {
        return tabCount
    }

}