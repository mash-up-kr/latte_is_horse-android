package com.mashup.latte.ui.record

import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.mashup.latte.R
import com.mashup.latte.ui.main.DrunkFragment
import com.mashup.latte.ui.record.adapter.RecordViewPagerAdapter
import kotlinx.android.synthetic.main.activity_record.*
import java.util.*


class RecordActivity : AppCompatActivity() {

    private var progressPosition = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        init()
    }


    private fun init() {
        initEvent()
        initViewPager()
    }

    private fun initEvent() {
    }


    private fun initViewPager() {
        //서버로 부터 받아온 페이지수 추가
        val fragmentList = ArrayList<Fragment>().apply {
            add(DrunkFragment.newInstance())
            add(DrunkFragment.newInstance())
            add(DrunkFragment.newInstance())
        }


        viewPagerRecord.apply {
            adapter = RecordViewPagerAdapter(supportFragmentManager, fragmentList)
            offscreenPageLimit = 3
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
                }
            })
        }
    }

    //progressAnimate
    fun animateProgress(position: Int) {
        val coloredWidthProgressAnimator: ValueAnimator = ValueAnimator.ofFloat(
            (PROGRESS_WEIGHT_STEP * progressPosition),
            (PROGRESS_WEIGHT_STEP * (position + 1))
        ).apply {
            duration = 300
            addUpdateListener {
                val value: Float = it.animatedValue as Float
                (viewRecordProgressColored.layoutParams as (LinearLayout.LayoutParams)).weight =
                    value
                viewRecordProgressColored.requestLayout()
            }
        }
        coloredWidthProgressAnimator.start()
        progressPosition = (position + 1)
    }

    companion object {
        const val PROGRESS_WEIGHT_STEP = 0.1f
    }

}
