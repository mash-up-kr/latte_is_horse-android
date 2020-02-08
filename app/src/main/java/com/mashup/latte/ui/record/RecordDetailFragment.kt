package com.mashup.latte.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.ext.showDateDialog
import com.mashup.latte.ui.record.adapter.RecordDetailRecyclerViewAdapter
import com.mashup.latte.ui.record.data.*
import kotlinx.android.synthetic.main.fragment_record_detail.*
import java.util.*

/**
 * Created by Namget on 2019.11.23.
 */
class RecordDetailFragment : Fragment() {

    private val recordDetailRecyclerViewAdapter: RecordDetailRecyclerViewAdapter by lazy {
        RecordDetailRecyclerViewAdapter()
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
    }

    private fun initView() {
        btnDetailDateSelector.setOnClickListener {
            Calendar.getInstance().showDateDialog(requireContext(), { result ->
                (it as Button).text = result
            })
        }
        initRecyclerView()
        initHangoverStatusImageView()
        initAlcoholImageView()
        initDrunkenStatusImageView()
    }

    private fun initRecyclerView() {
        recyclerViewDetailAlcohol.apply {
            adapter = recordDetailRecyclerViewAdapter
            setHasFixedSize(true)
        }
    }

    private fun initDrunkenStatusImageView(){
        val statusClickListener: View.OnClickListener = View.OnClickListener {
            when (it.id) {
                R.id.txtDetailDrunkenNothing -> {
                    txtDetailDrunkenNothing.isSelected = true
                    txtDetailDrunkenlittle.isSelected = false
                    txtDetailDrunkenMuch.isSelected = false
                    txtDetailDrunkenDog.isSelected = false
                }
                R.id.txtDetailDrunkenlittle -> {
                    txtDetailDrunkenNothing.isSelected = false
                    txtDetailDrunkenlittle.isSelected = true
                    txtDetailDrunkenMuch.isSelected = false
                    txtDetailDrunkenDog.isSelected = false
                }
                R.id.txtDetailDrunkenMuch -> {
                    txtDetailDrunkenNothing.isSelected = false
                    txtDetailDrunkenlittle.isSelected = false
                    txtDetailDrunkenMuch.isSelected = true
                    txtDetailDrunkenDog.isSelected = false
                }
                R.id.txtDetailDrunkenDog -> {
                    txtDetailDrunkenNothing.isSelected = false
                    txtDetailDrunkenlittle.isSelected = false
                    txtDetailDrunkenMuch.isSelected = false
                    txtDetailDrunkenDog.isSelected = true
                }
            }
        }
        txtDetailDrunkenNothing.isSelected = true
        txtDetailDrunkenNothing.setOnClickListener(statusClickListener)
        txtDetailDrunkenlittle.setOnClickListener(statusClickListener)
        txtDetailDrunkenMuch.setOnClickListener(statusClickListener)
        txtDetailDrunkenDog.setOnClickListener(statusClickListener)
    }

    private fun initHangoverStatusImageView() {
        val statusClickListener: View.OnClickListener = View.OnClickListener {
            when (it.id) {
                R.id.imgDetailHangoverStatusSangque -> {
                    imgDetailHangoverStatusSangque.isSelected = true
                    imgDetailHangoverStatusEueuk.isSelected = false
                    imgDetailHangoverStatusDeath.isSelected = false
                }
                R.id.imgDetailHangoverStatusEueuk -> {
                    imgDetailHangoverStatusSangque.isSelected = false
                    imgDetailHangoverStatusEueuk.isSelected = true
                    imgDetailHangoverStatusDeath.isSelected = false
                }
                R.id.imgDetailHangoverStatusDeath -> {
                    imgDetailHangoverStatusSangque.isSelected = false
                    imgDetailHangoverStatusEueuk.isSelected = false
                    imgDetailHangoverStatusDeath.isSelected = true
                }
            }
        }
        imgDetailHangoverStatusSangque.setOnClickListener(statusClickListener)
        imgDetailHangoverStatusSangque.isSelected = true
        imgDetailHangoverStatusEueuk.setOnClickListener(statusClickListener)
        imgDetailHangoverStatusDeath.setOnClickListener(statusClickListener)
    }

    private fun initAlcoholImageView() {
        imgDetailSoju.setOnClickListener {
            recordDetailRecyclerViewAdapter.addAlcohol(Soju())
        }
        imgDetailBeer.setOnClickListener{
            recordDetailRecyclerViewAdapter.addAlcohol(Beer())
        }
        imgDetailWine.setOnClickListener{
            recordDetailRecyclerViewAdapter.addAlcohol(Wine())
        }
        imgDetailRiceWine.setOnClickListener{
            recordDetailRecyclerViewAdapter.addAlcohol(RiceWine())
        }
        imgDetailLiquor.setOnClickListener{
            recordDetailRecyclerViewAdapter.addAlcohol(Liquor())
        }
        imgDetailEtc.setOnClickListener{
            recordDetailRecyclerViewAdapter.addAlcohol(AlcoholEtc())
        }
    }


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


}