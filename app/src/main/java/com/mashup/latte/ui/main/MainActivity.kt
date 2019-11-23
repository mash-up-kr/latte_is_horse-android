package com.mashup.latte.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.mashup.latte.R
import com.mashup.latte.ui.main.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        initViewPager()
        initTabLayout()
    }


    private fun initViewPager() {
        val fragmentList = ArrayList<Fragment>().apply {
            add(DrunkFragment.newInstance())
            add(QuestionFragment.newInstance())
        }

        container.apply {
            adapter = MainViewPagerAdapter(supportFragmentManager, fragmentList)
            offscreenPageLimit = 2
            currentItem = 0
        }
    }

    private fun initTabLayout() {
        tabLayout.setupWithViewPager(container)
    }
}

