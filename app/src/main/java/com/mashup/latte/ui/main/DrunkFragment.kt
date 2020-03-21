package com.mashup.latte.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.ui.main_detail.MainDetailActivity
import kotlinx.android.synthetic.main.fragment_main_drunk.*
import java.lang.StringBuilder

/**
 * Created by Namget on 2019.11.23.
 */
class DrunkFragment : Fragment() {
    lateinit var alcoholDiary: AlcoholDiary

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_drunk, container, false)

        view.setOnClickListener {
            val intent = Intent(activity, MainDetailActivity::class.java)
            intent.putExtra(MainActivity.DATA_DIARY, alcoholDiary.id)
            startActivity(intent)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val diary =
            arguments?.getParcelable<AlcoholDiary>(MainActivity.DATA_DIARY) ?: AlcoholDiary()
        initUI(diary)
    }

    private fun initUI(diary: AlcoholDiary) {
        alcoholDiary = diary.apply {
            val diaryDate = date.split(".")
            txtMainYear.text = diaryDate[0]
            txtMainDate.text = "${diaryDate[1]}.${diaryDate[2]}"

            val countBuilder: StringBuilder = StringBuilder()
            var index = 1
            for (drinkCount in diary.drunkenAmounts) {
                countBuilder.append("${drinkCount.name}(${drinkCount.type}) ${drinkCount.bottle}병 ${drinkCount.cup}잔")
                if (index != diary.drunkenAmounts.size)
                    countBuilder.append("\n")
            }
            txtMainDrunkBottleCount.isSelected = true
            ImgMainDrunkImage.load(getFrameImage(diary.drunkenActionType))
            txtMainDrunkBottleCount.text = countBuilder.toString()
            txtMainDrunkHangover.text = diary.hanoverStatus
            txtMainDrunkAmount.text = diary.drunkenStatus
        }
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

    companion object {
        const val DATA_DIARY_ID = "data_diary_id"
        private lateinit var drunkFragment: DrunkFragment
        fun newInstance(args: Bundle?): DrunkFragment {
            synchronized(DrunkFragment::class) {
                drunkFragment = DrunkFragment()
                if (args != null) {
                    drunkFragment.arguments = args
                }
                return drunkFragment
            }
        }
    }

}