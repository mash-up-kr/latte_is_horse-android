package com.mashup.latte.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mashup.latte.R

/**
 * Created by Namget on 2019.11.23.
 */
class RecordImageFragment : Fragment() {

    companion object {
        lateinit var recordImageFragment: RecordImageFragment

        fun newInstance(): RecordImageFragment {
            synchronized(RecordImageFragment::class) {
                recordImageFragment = RecordImageFragment()
                val args = Bundle()
                recordImageFragment.arguments = args
                return recordImageFragment
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}