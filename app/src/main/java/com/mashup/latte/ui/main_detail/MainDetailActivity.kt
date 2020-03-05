package com.mashup.latte.ui.main_detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mashup.latte.R
import com.mashup.latte.ext.startActivity
import com.mashup.latte.ui.main_detail.adapter.MainDetailRecyclerViewAdapter
import com.mashup.latte.ui.main_detail.adapter.MainDetailViewPagerAdapter
import com.mashup.latte.ui.main_detail.data.MainDetailImages
import com.mashup.latte.ui.record.RecordActivity
import com.mashup.latte.ui.record.decoration.RecyclerViewDivWidthDecoration
import kotlinx.android.synthetic.main.activity_main_detail.*

class MainDetailActivity : AppCompatActivity(), MainDetailRecyclerViewAdapter.OnImageClickListener{

    private var isViewPagerOn = false
    private val imageList = ArrayList<String>()

    private val mainDetailRecyclerViewAdapter: MainDetailRecyclerViewAdapter by lazy {
        MainDetailRecyclerViewAdapter(this)
    }

    private val mainDetailViewPagerAdapter: MainDetailViewPagerAdapter by lazy {
        MainDetailViewPagerAdapter(this, imageList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)
        init()
    }

    private fun init() {
        getData()
        initEvent()
        initRecyclerView()
        initViewPager()
    }

    private fun initRecyclerView() {
        recyclerViewMainDetail.apply {
            adapter = mainDetailRecyclerViewAdapter
            addItemDecoration(RecyclerViewDivWidthDecoration(20))
            setHasFixedSize(true)
        }

        for(i in imageList){
            mainDetailRecyclerViewAdapter.addImages(MainDetailImages())
        }
    }

    override fun onImageClick(position: Int) {
        layoutMainDetailBackground.visibility = View.VISIBLE
        viewPagerMainDetail.currentItem = position
        isViewPagerOn = true
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
            isViewPagerOn = false
        }

        ImgMainDetailBack.setOnClickListener {
            finish()
        }

        txtMainDetailModify.setOnClickListener{
            startActivity<RecordActivity>()
        }
    }

    private fun getData() {
        //TODO 서버에서 데이터 받아오기

        imageList.add("1")
        imageList.add("2")
        imageList.add("3")
        imageList.add("4")
        imageList.add("5")
    }

    override fun onBackPressed() {
        if(isViewPagerOn){
            layoutMainDetailBackground.visibility = View.GONE
            isViewPagerOn = false
        }
        else
            finish()
    }
}