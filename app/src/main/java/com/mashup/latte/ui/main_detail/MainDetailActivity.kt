package com.mashup.latte.ui.main_detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mashup.latte.R
import com.mashup.latte.ui.main_detail.adapter.MainDetailRecyclerViewAdapter
import com.mashup.latte.ui.main_detail.data.MainDetailImages
import com.mashup.latte.ui.record.decoration.RecyclerViewDivWidthDecoration
import kotlinx.android.synthetic.main.activity_main_detail.*

class MainDetailActivity : AppCompatActivity(), MainDetailRecyclerViewAdapter.OnImageClickListener{

    private val mainDetailRecyclerViewAdapter: MainDetailRecyclerViewAdapter by lazy {
        MainDetailRecyclerViewAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)
        init()
    }

    private fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerViewMainDetail.apply {
            adapter = mainDetailRecyclerViewAdapter
            addItemDecoration(RecyclerViewDivWidthDecoration(20))
            setHasFixedSize(true)
        }
        mainDetailRecyclerViewAdapter.addImages(MainDetailImages())
        mainDetailRecyclerViewAdapter.addImages(MainDetailImages())
        mainDetailRecyclerViewAdapter.addImages(MainDetailImages())
    }

    override fun onImageClick(position: Int) {
        layoutMainDetailBackground.visibility = View.VISIBLE
    }
}