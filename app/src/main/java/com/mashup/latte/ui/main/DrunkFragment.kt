package com.mashup.latte.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.data.datasource.local.entity.AlcoholDiary
import com.mashup.latte.data.dto.response.Diary
import com.mashup.latte.ui.main_detail.MainDetailActivity
import kotlinx.android.synthetic.main.fragment_main_drunk.*
import org.koin.core.logger.MESSAGE
import java.lang.StringBuilder

/**
 * Created by Namget on 2019.11.23.
 */
class DrunkFragment : Fragment() {

    companion object {
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_drunk, container, false)

        view.setOnClickListener {
            val intent = Intent(activity, MainDetailActivity::class.java)
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
        diary.apply {
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
            txtMainDrunkBottleCount.text = countBuilder.toString()
            txtMainDrunkHangover.text = diary.hanoverStatus
            txtMainDrunkAmount.text = diary.drunkenStatus

        }
    }

}