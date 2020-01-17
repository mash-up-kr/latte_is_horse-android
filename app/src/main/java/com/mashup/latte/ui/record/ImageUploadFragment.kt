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
class ImageUploadFragment : Fragment() {

    companion object {
        lateinit var imageUploadFragment: ImageUploadFragment

        fun newInstance(): ImageUploadFragment {
            synchronized(ImageUploadFragment::class) {
                imageUploadFragment = ImageUploadFragment()
                val args = Bundle()
                imageUploadFragment.arguments = args
                return imageUploadFragment
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_drunk, container, false)
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