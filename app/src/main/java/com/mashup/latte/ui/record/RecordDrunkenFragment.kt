package com.mashup.latte.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mashup.latte.R

/**
 * Created by Namget on 2020.01.20.
 */
class RecordDrunkenFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_drunken, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        lateinit var recordDrunkenFragment: RecordDrunkenFragment

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