package com.mashup.latte.ui.record

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.ext.showDateDialog
import com.mashup.latte.ui.record.adapter.RecordDrunkenRecyclerViewAdapter
import com.mashup.latte.ui.record.decoration.RecyclerViewDivHeightDecoration
import com.mashup.latte.ui.record.decoration.RecyclerViewDivWidthDecoration
import kotlinx.android.synthetic.main.fragment_record_detail.*
import kotlinx.android.synthetic.main.fragment_record_drunken.*
import java.util.*

/**
 * Created by Namget on 2020.01.20.
 */
class RecordDrunkenFragment : Fragment() {

    private val recordDrunkenRecyclerViewAdapter: RecordDrunkenRecyclerViewAdapter by lazy {
        RecordDrunkenRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_drunken, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }


    private fun init() {
        initView()
    }

    private fun initView() {
        initRecyclerView()
        initTextWatcher()
    }

    private fun initTextWatcher() {
        txtDrunkenAfterTextLength.text =
            (String.format(getString(R.string.record_detail_drunken_after_text_watch), 0))
        txtDetailStatusTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txtDrunkenAfterTextLength.text = (String.format(
                    getString(R.string.record_detail_drunken_after_text_watch),
                    txtDetailStatusTitle.length()
                ))
            }
        })
    }

    private fun initRecyclerView() {
        recyclerViewDrunken.apply {
            adapter = recordDrunkenRecyclerViewAdapter
            addItemDecoration(RecyclerViewDivWidthDecoration(20))
            setHasFixedSize(true)
            animation = null
        }
    }


    companion object {
        private lateinit var recordDrunkenFragment: RecordDrunkenFragment

        fun newInstance(): RecordDrunkenFragment {
            synchronized(RecordDrunkenFragment::class) {
                recordDrunkenFragment = RecordDrunkenFragment()
                val args = Bundle()
                recordDrunkenFragment.arguments = args
                return recordDrunkenFragment
            }
        }
    }

}