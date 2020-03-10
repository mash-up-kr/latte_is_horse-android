package com.mashup.latte.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.ext.startActivity
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

    private fun initViewPager() {
        compositeDisposable.add(
            repository.getDiaries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ alcoholDiaries ->
                    Log.d("getDiaries", alcoholDiaries.toString())
                    updateUI(alcoholDiaries)
                }, {
                })
        )
    }

    private fun updateUI(diaries: List<AlcoholDiary>) {
        //서버로 부터 받아온 페이지수 추가
        if (diaries.isEmpty()) {
            imgMainNone.visibility = View.VISIBLE
            frameMainNone.visibility = View.VISIBLE
            viewPagerMain.visibility = View.GONE
            return
        }

        val fragmentList = ArrayList<Fragment>()
        for(diary in diaries){
            val bundle = Bundle()
            bundle.putParcelable(DATA_DIARY,diary)
            fragmentList.add(DrunkFragment.newInstance(bundle))
        }

        viewPagerMain.apply {
            adapter = MainViewPagerAdapter(supportFragmentManager, fragmentList)
            currentItem = PAGE_INITIAL_ITEM
            pageMargin = PAGE_MARGIN
        }
    }


    companion object {
        const val PAGE_LIMIT = 1
        const val PAGE_MARGIN = 50
        const val PAGE_INITIAL_ITEM = 0
        const val DATA_DIARY = "data_diary"
    }
}

