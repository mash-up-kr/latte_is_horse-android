package com.mashup.latte.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.mashup.latte.R
import com.mashup.latte.ui.main.adapter.MainViewPagerAdapter
import com.mashup.latte.ui.add.AddActivity
import com.mashup.latte.ui.statistic.StatisticActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
    }


    private fun init() {
        initEvent()
        initViewPager()
        initTabLayout()
    }

    private fun initEvent() {
        fab.setOnClickListener {
            val nextIntent = Intent(this, AddActivity::class.java)
            startActivity(nextIntent)
        }
        statisticLayout.setOnClickListener {
            val nextIntent = Intent(this, StatisticActivity::class.java)
            startActivity(nextIntent)
        }
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

