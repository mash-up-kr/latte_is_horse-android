package com.mashup.latte.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.ext.startActivity
import com.mashup.latte.data.dto.response.AlcoholLevel
import com.mashup.latte.data.dto.response.Diary
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.ui.main.adapter.MainViewPagerAdapter
import com.mashup.latte.ui.record.RecordActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var viewPagerFragment: ArrayList<Fragment>
    lateinit var compositeDisposable: CompositeDisposable
    private val repository: ApiRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun init() {
        compositeDisposable = CompositeDisposable()
        initEvent()
        initViewPager()
        //requestDrunkItem()
    }

    private fun initEvent() {
        btnMainRecord.setOnClickListener {
            startActivity<RecordActivity>()
        }
    }


    private fun requestDrunkItem() {
        initViewPager()
    }

    private fun initViewPager() {
        compositeDisposable.add(
            repository.getDiaries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({diariesResponse ->
                    Log.d("getDiaries", diariesResponse.toString())
                    // TODO: ViewPager 반영
                }, {
                })
        )

        //서버로 부터 받아온 페이지수 추가
        val fragmentList = ArrayList<Fragment>().apply {
            val alcoholRecords = ArrayList<AlcoholLevel>()
            alcoholRecords.add(
                AlcoholLevel(
                    2.0,
                    2.5,
                    1
                )
            )

//            val bundle = Bundle()
//            bundle.putSerializable("diary",
//                Diary(
//                    1,
//                    "굿",
//                    "18:28:56",
//                    "쏘쏘",
//                    "투뱃",
//                    "굿",
//                    alcoholRecords
//                )
//            )
//
//            add(DrunkFragment.newInstance(bundle))
//            add(DrunkFragment.newInstance(bundle))
//            add(DrunkFragment.newInstance(bundle))
//            add(DrunkFragment.newInstance(bundle))
//            add(DrunkFragment.newInstance(bundle))
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

