package com.mashup.latte.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.ext.e
import com.mashup.latte.ext.startActivity
import com.mashup.latte.ext.startActivityResult
import com.mashup.latte.ui.base.BaseActivity
import com.mashup.latte.ui.main.adapter.MainViewPagerAdapter
import com.mashup.latte.ui.record.RecordActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*


class MainActivity : BaseActivity() {

    private lateinit var viewPagerFragment: ArrayList<Fragment>
    private val repository: ApiRepository by inject()
    private lateinit var fromDate: Date
    private lateinit var toDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initEvent()
        initDate()
        initViewPager()
        //requestDrunkItem()
    }

    private fun initEvent() {
        btnMainRecord.setOnClickListener {
            startActivityResult<RecordActivity>(REQ_RECORD)
        }
    }

    private fun initViewPager() {
        disposable.add(
            repository.getDiaries(fromDate, toDate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ alcoholDiaries ->
                    e("getDiaries", alcoholDiaries.toString())
                    updateUI(alcoholDiaries)
                }, {
                })
        )
    }

    private fun initDate() {
        val calendar = Calendar.getInstance().also {
            it.set(Calendar.YEAR, 1970)
        }
        fromDate = calendar.time
        toDate = Date()
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
        for (diary in diaries) {
            val bundle = Bundle()
            bundle.putParcelable(DATA_DIARY, diary)
            fragmentList.add(DrunkFragment.newInstance(bundle))
        }

        viewPagerMain.apply {
            adapter = MainViewPagerAdapter(supportFragmentManager, fragmentList)
            currentItem = PAGE_INITIAL_ITEM
            pageMargin = PAGE_MARGIN
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_RECORD) {
                initViewPager()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val PAGE_LIMIT = 1
        const val PAGE_MARGIN = 50
        const val PAGE_INITIAL_ITEM = 0
        const val REQ_RECORD = 100
        const val DATA_DIARY = "data_diary"
    }
}

