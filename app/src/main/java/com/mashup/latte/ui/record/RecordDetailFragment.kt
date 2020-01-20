package com.mashup.latte.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.ext.showDateDialog
import kotlinx.android.synthetic.main.fragment_record_detail.*
import java.util.*

/**
 * Created by Namget on 2019.11.23.
 */
class RecordDetailFragment : Fragment() {

    companion object {
        lateinit var recordDetailFragment: RecordDetailFragment

        fun newInstance(): RecordDetailFragment {
            synchronized(RecordDetailFragment::class) {
                recordDetailFragment = RecordDetailFragment()
                val args = Bundle()
                recordDetailFragment.arguments = args
                return recordDetailFragment
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        initView()
        initRecyclerView()
    }

    private fun initView() {
        btnDetailDateSelector.setOnClickListener {
            Calendar.getInstance().showDateDialog(requireContext(), { result ->
                (it as Button).text = result
            })
        }
    }

    private fun initRecyclerView() {

    }


}