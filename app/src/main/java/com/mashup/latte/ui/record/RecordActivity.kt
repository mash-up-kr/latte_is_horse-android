package com.mashup.latte.ui.record

import android.Manifest
import android.animation.ValueAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.mashup.latte.R
import com.mashup.latte.ui.record.adapter.RecordViewPagerAdapter
import com.mashup.latte.ui.record.data.DrunkenAfter
import com.mashup.latte.util.PermissionManager
import kotlinx.android.synthetic.main.activity_record.*
import org.koin.android.logger.AndroidLogger
import java.util.*


class RecordActivity : AppCompatActivity() {

    private var progressCurrentPage = 1
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
                onComplete()

            } else {
                viewPagerRecord.currentItem = currentCount + 1
            }
        }
    }

    private fun onComplete() {

    }

    private fun bringImageData() {
        (fragmentList[0] as RecordImageFragment).giveImageData()
    }

    private fun bringDrunkenData() {
        (fragmentList[1] as RecordDrunkenFragment).giveDrunkenData()
    }

    private fun bringDetailData() {
        (fragmentList[2] as RecordDetailFragment).giveDetailData()
    }


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
                    //TODO
                } else {
                    finish()
                }
                return
            }
        }
    }

}
