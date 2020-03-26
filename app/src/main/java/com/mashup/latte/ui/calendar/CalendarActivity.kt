package com.mashup.latte.ui.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.ui.main.MainActivity
import com.mashup.latte.ui.main_detail.MainDetailActivity
import kotlinx.android.synthetic.main.activity_calendar.*

/**
 * Created by Arim on 2020.03.24.
 */
class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarManager: CalendarManager
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var diaryData: ArrayList<AlcoholDiary>
    private lateinit var alcoholDiary: AlcoholDiary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        init()
        initListener()
        showDataLayout.visibility = View.INVISIBLE
        dotInfo.visibility = View.VISIBLE
    }

    private fun init() {
        initDiaryData()
        initEvent()
        recyclerCalendar.apply {
            setHasFixedSize(true)
            adapter = calendarAdapter
        }
    }

    private fun initEvent() {
        ImgCalendarBack.setOnClickListener{
            finish()
        }
    }

    private fun initListener() {
        calendar_prev_button.setOnClickListener {
            calendarAdapter.prevArrow()
            txtCalendarDate.text = calendarManager.nowDate
        }
        calendar_next_button.setOnClickListener {
            calendarAdapter.nextArrow()
            txtCalendarDate.text = calendarManager.nowDate
        }
        txtCalendarDate.text = calendarManager.nowDate
        btnDetail.setOnClickListener {
            val intent = Intent(this, MainDetailActivity::class.java)
            intent.putExtra(MainActivity.DATA_DIARY, alcoholDiary.id)
            startActivity(intent)
        }
    }

    private fun initDiaryData(){
        val passedIntent = getIntent()
        diaryData = passedIntent.getParcelableArrayListExtra(MainActivity.DATA_DIARY)
        calendarManager = CalendarManager(diaryData)
        calendarAdapter = CalendarAdapter(calendarManager, this)
    }

    fun showDiaryData(dot: Boolean, date: String){
        if(dot){
            dotInfo.visibility = View.INVISIBLE
            showDataLayout.visibility = View.VISIBLE
            setShowData(date)
        }else{
            showDataLayout.visibility = View.INVISIBLE
            dotInfo.visibility = View.VISIBLE
        }
    }

    private fun setShowData(date: String){
        for(diary in diaryData){
            if(diary.date == date){
                alcoholDiary = diary
                dataImage.load(getFrameImage(diary.drunkenActionType))

                val countBuilder: StringBuilder = StringBuilder()
                var index = 1
                for (drinkCount in diary.drunkenAmounts) {
                    countBuilder.append("${drinkCount.name}(${drinkCount.type}) ${drinkCount.bottle}병 ${drinkCount.cup}잔")
                    if (index != diary.drunkenAmounts.size)
                        countBuilder.append("\n")
                }
                text_amount.text = countBuilder.toString()
                text_clean.text = diary.hanoverStatus
                text_drunk.text = diary.drunkenStatus
            }
        }
    }

    private fun getFrameImage(actionType: String): Int {
        when (actionType) {
            "완전멀쩡" -> {
                return R.drawable.img_drunken_mulggyung
            }
            "살짝알딸딸" -> {
                return R.drawable.img_drunken_alddalddal
            }
            "음주가무" -> {
                return R.drawable.img_drunken_eumjugamu
            }
            "무지개토" -> {
                return R.drawable.img_drunken_rainbow_vomit
            }
            "눈물줄줄" -> {
                return R.drawable.img_drunken_crying
            }
            "스킨십귀신" -> {
                return R.drawable.img_drunken_skinship
            }
            "필름끊김" -> {
                return R.drawable.img_drunken_blackout
            }
            "꿀잠쿨쿨" -> {
                return R.drawable.img_drunken_honey_sleep
            }
            "귀가요정" -> {
                return R.drawable.img_drunken_go_home_fairy
            }
            else -> {
                return R.drawable.img_drunken_mulggyung
            }
        }
    }
}
