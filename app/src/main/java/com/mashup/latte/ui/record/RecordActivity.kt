package com.mashup.latte.ui.record

import android.Manifest
import android.animation.ValueAnimator
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.mashup.latte.R
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.repository.ApiRepository
import com.mashup.latte.pref.UserPref
import com.mashup.latte.ui.record.adapter.RecordViewPagerAdapter
import com.mashup.latte.ui.record.data.result.DetailResult
import com.mashup.latte.ui.record.data.result.DrunkenResult
import com.mashup.latte.ui.record.data.result.ImageResult
import com.mashup.latte.util.PermissionManager
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


class RecordActivity : AppCompatActivity() {

    private var progressCurrentPage = 1
    private val repository: ApiRepository by inject()
    private val userPref: UserPref by inject()
    private val fragmentList = ArrayList<Fragment>().apply {
        add(RecordImageFragment.newInstance())
        add(RecordDetailFragment.newInstance())
        add(RecordDrunkenFragment.newInstance())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        init()
    }


    private fun init() {
        initPermissionCheck()
        initEvent()
        initViewPager()
    }

    private fun initPermissionCheck() {
        PermissionManager.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun initEvent() {
        ImgRecordBack.setOnClickListener {
            finish()
        }
        txtRecordNext.setOnClickListener {
            val currentCount = viewPagerRecord.currentItem
            if (PROGRESS_PAGE_COUNT == (currentCount + 1)) {
                //TODO 완료처리

                lifecycleScope.launch(Dispatchers.Main) {
                    onComplete()
                }
            } else {
                viewPagerRecord.currentItem = currentCount + 1
            }
        }
    }

    private fun onComplete() {
        val imageData = bringImageData()
        val detailData = bringDetailData()
        val drunkenData = bringDrunkenData()

        if (detailData != null && drunkenData != null) {
            lifecycleScope.launch(Dispatchers.IO) {
                val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
                val timestamp = simpleDateFormat.parse(detailData.date)

                val alcoholDiary = AlcoholDiary(
                    null,
                    detailData.date,
                    detailData.drunkenStatus,
                    detailData.hanoverStatus,
                    detailData.drunkenAmounts,
                    drunkenData.drunkenActionType,
                    drunkenData.review,
                    imageData?.imagePath,
                    timestamp
                )
                repository.insertAlcoholDiary(alcoholDiary)
                finish()
            }
        }
    }

    private fun bringImageData(): ImageResult? =
        (fragmentList[0] as RecordImageFragment).giveImageData()

    private fun bringDetailData(): DetailResult? =
        (fragmentList[1] as RecordDetailFragment).giveDetailData()

    private fun bringDrunkenData(): DrunkenResult? =
        (fragmentList[2] as RecordDrunkenFragment).giveDrunkenData()


    private fun initViewPager() {
        //서버로 부터 받아온 페이지수 추가

        viewPagerRecord.apply {
            adapter = RecordViewPagerAdapter(supportFragmentManager, fragmentList)
            offscreenPageLimit = PROGRESS_PAGE_COUNT
            currentItem = 0
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    animateProgress(position)
                    updateUI(position)
                }
            })
        }
    }

    private fun updateUI(position: Int) {
        if (PROGRESS_PAGE_COUNT == (position + 1)) txtRecordNext.text =
            getString(R.string.all_complete)
        else txtRecordNext.text = getString(R.string.all_next)
    }

    //progressAnimate
    private fun animateProgress(position: Int) {
        val coloredWidthProgressAnimator: ValueAnimator = ValueAnimator.ofFloat(
            (PROGRESS_WEIGHT_STEP * progressCurrentPage),
            (PROGRESS_WEIGHT_STEP * (position + 1))
        ).apply {
            duration = PROGRESS_DURATION
            addUpdateListener {
                val value: Float = it.animatedValue as Float
                (viewRecordProgressColored.layoutParams as (LinearLayout.LayoutParams)).weight =
                    value
                viewRecordProgressColored.requestLayout()
            }
        }
        coloredWidthProgressAnimator.start()
        progressCurrentPage = (position + 1)
    }

    companion object {
        const val PROGRESS_PAGE_COUNT = 3
        const val PROGRESS_WEIGHT_STEP = 0.1f
        const val PROGRESS_DURATION = 200L
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PermissionManager.REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    (fragmentList[0] as RecordImageFragment).onReload()
                    //TODO
                } else {
                    finish()
                }
                return
            }
        }
    }

}
