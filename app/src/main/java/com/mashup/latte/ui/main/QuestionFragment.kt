package com.mashup.latte.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * Created by Namget on 2019.11.23.
 */
class QuestionFragment : Fragment() {

    companion object {
        private lateinit var questionFragment: QuestionFragment

        fun newInstance(): QuestionFragment {
            synchronized(QuestionFragment::class) {
                questionFragment = QuestionFragment()
                val args = Bundle()
                questionFragment.arguments = args
                return questionFragment
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        initView()
    }

    private fun initView() {
        questionYesBtn.setOnClickListener {
            questionYesBtn.isSelected = true
            questionNoBtn.isSelected = false
        }
        questionNoBtn.setOnClickListener {
            questionYesBtn.isSelected = false
            questionNoBtn.isSelected = true
        }
    }

}