package com.mashup.latte.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.ext.e
import com.mashup.latte.ext.startActivityResult
import com.mashup.latte.ui.base.BaseActivity
import com.mashup.latte.ui.main.adapter.MainViewPagerAdapter
import com.mashup.latte.ui.record.RecordActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*


class MainActivity : BaseActivity() {

    private lateinit var viewPagerFragment: ArrayList<Fragment>
    private val repository: ApiRepository by inject()
    private lateinit var thisMonth: Date
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
            repository.getDiariesThisMonth(thisMonth)
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
        val calendar = Calendar.getInstance()
        thisMonth = calendar.time
    }

    private fun updateUI(diaries: List<AlcoholDiary>) {
        //서버로 부터 받아온 페이지수 추가
        if (diaries.isEmpty()) {
            imgMainNone.visibility = View.VISIBLE
            frameMainNone.visibility = View.VISIBLE
            viewPagerMain.visibility = View.GONE
            return
        } else if (imgMainNone.visibility == View.VISIBLE){
            imgMainNone.visibility = View.GONE
            frameMainNone.visibility = View.GONE
            viewPagerMain.visibility = View.VISIBLE
        }

        val fragmentList = ArrayList<Fragment>()
        for (diary in diaries) {
            val bundle = Bundle()
            bundle.putParcelable(DATA_DIARY, diary)
            fragmentList.add(DrunkFragment.newInstance(bundle))
        }

        viewPagerMain.apply {
            if (diaries.size == 1) {
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val screenWidth = convertPixelsToDp(displayMetrics.widthPixels*1.0f, context)
                val viewPagerWidth = convertPixelsToDp(viewPagerMain.width*1.0f, context)
                val itemWidth = viewPagerWidth * 0.8f
                viewPagerMainLayout.x = convertDpToPixel((screenWidth - itemWidth)*0.5f, context)
            } else {
                viewPagerMainLayout.x = convertDpToPixel(28f, context)
            }
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

    private fun convertDpToPixel(dp: Float, context: Context?): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    private fun convertPixelsToDp(px: Float, context: Context?): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    companion object {
        const val PAGE_LIMIT = 1
        const val PAGE_MARGIN = 50
        const val PAGE_INITIAL_ITEM = 0
        const val REQ_RECORD = 100
        const val DATA_DIARY = "data_diary"
    }
}

