package com.mashup.latte.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.ext.startActivity
import com.mashup.latte.ui.main.adapter.MainViewPagerAdapter
import com.mashup.latte.ui.record.RecordActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var viewPagerFragment: ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initEvent()
        initViewPager()
        requestDrunkItem()
    }

    private fun initEvent() {
        txtMainRecord.setOnClickListener {
            startActivity<RecordActivity>()
        }
    }


    private fun requestDrunkItem() {
        initViewPager()
    }

    private fun initViewPager() {

        //서버로 부터 받아온 페이지수 추가
        val fragmentList = ArrayList<Fragment>().apply {
            add(DrunkFragment.newInstance())
            add(DrunkFragment.newInstance())
            add(DrunkFragment.newInstance())
            add(DrunkFragment.newInstance())
        }
        viewPagerMain.apply {
            adapter = MainViewPagerAdapter(supportFragmentManager, fragmentList)
            offscreenPageLimit = PAGE_LIMIT
            currentItem = PAGE_INITIAL_ITEM
            pageMargin = PAGE_MARGIN
        }
    }


    companion object{
        const val PAGE_LIMIT = 2
        const val PAGE_MARGIN = 50
        const val PAGE_INITIAL_ITEM = 0
    }
}

