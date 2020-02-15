package com.mashup.latte.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.data.Diary
import kotlinx.android.synthetic.main.fragment_main_drunk.view.*

/**
 * Created by Namget on 2019.11.23.
 */
class DrunkFragment : Fragment() {

    companion object {
        private lateinit var drunkFragment: DrunkFragment

        fun newInstance(args: Bundle? = null): DrunkFragment {
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

        val diary = arguments?.getSerializable("diary") as Diary?
        diary?.apply {
            /* TODO: View Update */
            view.txtMainDrunkAmount.text = drunken_level
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        requestTodayDrunkList()
    }

    private fun initRecyclerView() {

    }

    private fun requestTodayDrunkList() {

    }


}