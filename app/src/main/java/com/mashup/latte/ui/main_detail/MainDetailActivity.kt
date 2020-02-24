package com.mashup.latte.ui.main_detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mashup.latte.R
import com.mashup.latte.ui.main_detail.adapter.MainDetailRecyclerViewAdapter
import com.mashup.latte.ui.main_detail.adapter.MainDetailViewPagerAdapter
import com.mashup.latte.ui.main_detail.data.MainDetailImages
import com.mashup.latte.ui.record.decoration.RecyclerViewDivWidthDecoration
import kotlinx.android.synthetic.main.activity_main_detail.*

class MainDetailActivity : AppCompatActivity(), MainDetailRecyclerViewAdapter.OnImageClickListener{

    private var photoNum = 0

    private val mainDetailRecyclerViewAdapter: MainDetailRecyclerViewAdapter by lazy {
        MainDetailRecyclerViewAdapter(this)
    }

    private val mainDetailViewPagerAdapter: MainDetailViewPagerAdapter by lazy {
        MainDetailViewPagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)
        init()
    }

    private fun init() {
        initRecyclerView()
        initViewPager()
        initEvent()
    }

    private fun initRecyclerView() {
        recyclerViewMainDetail.apply {
            adapter = mainDetailRecyclerViewAdapter
            addItemDecoration(RecyclerViewDivWidthDecoration(20))
            setHasFixedSize(true)
        }

        for(i in 1..3){
            mainDetailRecyclerViewAdapter.addImages(MainDetailImages())
            photoNum++
        }
    }

    override fun onImageClick(position: Int) {
        layoutMainDetailBackground.visibility = View.VISIBLE
        viewPagerMainDetail.currentItem = position
    }

    private fun initViewPager() {
        viewPagerMainDetail.apply {
            adapter = mainDetailViewPagerAdapter
        }
        springDotsIndicator.setViewPager(viewPagerMainDetail)
    }

    private fun initEvent() {
        layoutMainDetailBackground.setOnClickListener{
            layoutMainDetailBackground.visibility = View.GONE
        }
    }
}