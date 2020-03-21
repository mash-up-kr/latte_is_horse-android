package com.mashup.latte.ui.main_detail

import android.os.Bundle
import android.view.View
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.ext.startActivity
import com.mashup.latte.ext.withScheduler
import com.mashup.latte.ui.base.BaseActivity
import com.mashup.latte.ui.main.MainActivity
import com.mashup.latte.ui.main_detail.adapter.MainDetailRecyclerViewAdapter
import com.mashup.latte.ui.main_detail.adapter.MainDetailViewPagerAdapter
import com.mashup.latte.ui.record.RecordActivity
import com.mashup.latte.ui.record.decoration.RecyclerViewDivWidthDecoration
import kotlinx.android.synthetic.main.activity_main_detail.*
import org.koin.android.ext.android.inject
import java.lang.StringBuilder

class MainDetailActivity : BaseActivity(), MainDetailRecyclerViewAdapter.OnImageClickListener {

    private var isViewPagerOn = false
    private val imageList: MutableList<String> = mutableListOf()
    private val repository: ApiRepository by inject()
    private var id: Long = 1


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
        getExta()
        getData()
        initEvent()
        initRecyclerView()
        initViewPager()
    }

    private fun getExta() {
        id = intent.getLongExtra(MainActivity.DATA_DIARY, 1)
    }

    private fun initRecyclerView() {
        recyclerViewMainDetail.apply {
            adapter = mainDetailRecyclerViewAdapter
            addItemDecoration(RecyclerViewDivWidthDecoration(20))
            setHasFixedSize(true)
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
        layoutMainDetailBackground.setOnClickListener {
            layoutMainDetailBackground.visibility = View.GONE
            isViewPagerOn = false
        }

        ImgMainDetailBack.setOnClickListener {
            finish()
        }

        txtMainDetailModify.setOnClickListener {
            startActivity<RecordActivity>()
        }
    }

    private fun getData() {
        disposable.add(
            repository.getDiaryById(id)
                .withScheduler()
                .subscribe({ alcoholDiary ->
                    alcoholDiary.apply {
                        imgMainDetail.load(getFrameImage(this.drunkenActionType))
                        txtMainDetailDate.text = this.date
                        txtMainDetailDrunk.text = this.drunkenStatus
                        txtMainDetailClean.text = this.hanoverStatus
                        txtMainDetailTalk.text = this.drunkenActionType

                        val countBuilder = StringBuilder()
                        var index = 1
                        for (drinkCount in this.drunkenAmounts) {
                            countBuilder.append("${drinkCount.name}(${drinkCount.type}) ${drinkCount.bottle}병 ${drinkCount.cup}잔")
                            if (index != this.drunkenAmounts.size)
                                countBuilder.append("\n")
                        }
                        txtMainDetailAmount.text = countBuilder.toString()

                        txtMainDetailDiary.text = this.review

                        val list = imagePath?.toList() ?: mutableListOf()
                        for (index in list.indices) {
                            val path = list[index]
                            if (!path.isNullOrEmpty()) {
                                imageList.add(path)
                            }
                        }
                        if (imageList.size > 0) {
                            mainDetailRecyclerViewAdapter.addImages(imageList)
                            mainDetailRecyclerViewAdapter.notifyDataSetChanged()
                            mainDetailViewPagerAdapter.addImages(imageList)
                            mainDetailViewPagerAdapter.notifyDataSetChanged()
                        }
                    }
                }, {

                })
        )
    }


    private fun getFrameImage(actionType: String): Int {
        when (actionType) {
            "완전멀쩡" -> {
                return R.drawable.ic_drunken_mulggyung_n
            }
            "살짝알딸딸" -> {
                return R.drawable.ic_drunken_alddalddal_n
            }
            "음주가무" -> {
                return R.drawable.ic_drunken_eumjugamu_n
            }
            "무지개토" -> {
                return R.drawable.ic_drunken_rainbow_vomit_n
            }
            "눈물줄줄" -> {
                return R.drawable.ic_drunken_crying_n
            }
            "스킨십귀신" -> {
                return R.drawable.ic_drunken_skinship_n
            }
            "필름끊김" -> {
                return R.drawable.ic_drunken_blackout_n
            }
            "꿀잠쿨쿨" -> {
                return R.drawable.ic_drunken_honey_sleep_n
            }
            "귀가요정" -> {
                return R.drawable.ic_drunken_go_home_fairy_n
            }
            else -> {
                return R.drawable.ic_drunken_mulggyung_n
            }
        }
    }

    override fun onBackPressed() {
        if (isViewPagerOn) {
            layoutMainDetailBackground.visibility = View.GONE
            isViewPagerOn = false
        } else
            finish()
    }
}