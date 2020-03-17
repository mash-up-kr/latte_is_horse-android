package com.mashup.latte.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mashup.latte.R
import com.mashup.latte.ext.toastMakeToast
import com.mashup.latte.ui.record.adapter.RecordDetailRecyclerViewAdapter
import com.mashup.latte.ui.record.data.alcohol.*
import com.mashup.latte.ui.record.data.result.DetailResult
import com.mashup.latte.ui.record.data.result.DrunkenAmount
import com.mashup.latte.ui.record.decoration.RecyclerViewDivHeightDecoration
import kotlinx.android.synthetic.main.fragment_record_detail.*


/**
 * Created by Namget on 2019.11.23.
 */
class RecordDetailFragment : Fragment() {
    private val recordDetailRecyclerViewAdapter: RecordDetailRecyclerViewAdapter by lazy {
        RecordDetailRecyclerViewAdapter()
    }

    private val detailData = DetailResult()

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
            RecordCalendarDialog(context = context!!, cancelCallback = {}) {
                btnDetailDateSelector.text = it
            }.show()
        }
        initRecyclerView()
        initHangoverStatusImageView()
        initAlcoholImageView()
        initDrunkenStatusImageView()
    }

    private fun initRecyclerView() {
        recyclerViewDetailAlcohol.apply {
            adapter = recordDetailRecyclerViewAdapter
            addItemDecoration(RecyclerViewDivHeightDecoration(20))
            setHasFixedSize(true)
        }
    }

    fun giveDetailData(): DetailResult? {
        if (btnDetailDateSelector.text.contains("날짜")) {
            context?.toastMakeToast("날짜를 입력하세요")
            return null
        }

        if (recyclerViewDetailAlcohol.childCount == 0) {
            context?.toastMakeToast("마신 술 종류를 입력하세요")
            return null
        }

        val list: MutableList<DrunkenAmount> = arrayListOf()

        for (i in 0 until recyclerViewDetailAlcohol.childCount) {
            val view =
                recyclerViewDetailAlcohol.getChildViewHolder(recyclerViewDetailAlcohol.getChildAt(i))
            val bottle = view.itemView.findViewById<EditText>(R.id.edtAlcoholBottle).text.toString()
            val cup = view.itemView.findViewById<EditText>(R.id.edtAlcoholBottle).text.toString()
            val name = view.itemView.findViewById<TextView>(R.id.txtItemAlcohol).text.toString()
            val type: String = if (name != "기타") {
                view.itemView.findViewById<Spinner>(R.id.spinnerAlcoholType)
                    .selectedItem.toString()
            } else {
                view.itemView.findViewById<EditText>(R.id.editTextAlcoholType).text.toString()
            }
            list.add(DrunkenAmount(name, type, cup, bottle))
        }
        detailData.drunkenAmounts = list
        detailData.date = btnDetailDateSelector.text.toString()

        return detailData
    }

    private fun initDrunkenStatusImageView() {
        val statusClickListener: View.OnClickListener = View.OnClickListener {
            when (it.id) {
                R.id.txtDetailDrunkenNothing -> {
                    detailData.drunkenStatus = "멀쩡함"
                    txtDetailDrunkenNothing.isSelected = true
                    txtDetailDrunkenlittle.isSelected = false
                    txtDetailDrunkenMuch.isSelected = false
                    txtDetailDrunkenDog.isSelected = false
                }
                R.id.txtDetailDrunkenlittle -> {
                    detailData.drunkenStatus = "조금취함"
                    txtDetailDrunkenNothing.isSelected = false
                    txtDetailDrunkenlittle.isSelected = true
                    txtDetailDrunkenMuch.isSelected = false
                    txtDetailDrunkenDog.isSelected = false
                }
                R.id.txtDetailDrunkenMuch -> {
                    detailData.drunkenStatus = "많이취함"
                    txtDetailDrunkenNothing.isSelected = false
                    txtDetailDrunkenlittle.isSelected = false
                    txtDetailDrunkenMuch.isSelected = true
                    txtDetailDrunkenDog.isSelected = false
                }
                R.id.txtDetailDrunkenDog -> {
                    detailData.drunkenStatus = "댕댕이"
                    txtDetailDrunkenNothing.isSelected = false
                    txtDetailDrunkenlittle.isSelected = false
                    txtDetailDrunkenMuch.isSelected = false
                    txtDetailDrunkenDog.isSelected = true
                }
            }
        }
        detailData.drunkenStatus = "멀쩡함"
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
                    detailData.hanoverStatus = "상쾌"
                    imgDetailHangoverStatusSangque.isSelected = true
                    imgDetailHangoverStatusEueuk.isSelected = false
                    imgDetailHangoverStatusDeath.isSelected = false
                }
                R.id.imgDetailHangoverStatusEueuk -> {
                    detailData.hanoverStatus = "으윽"
                    imgDetailHangoverStatusSangque.isSelected = false
                    imgDetailHangoverStatusEueuk.isSelected = true
                    imgDetailHangoverStatusDeath.isSelected = false
                }
                R.id.imgDetailHangoverStatusDeath -> {
                    detailData.hanoverStatus = "죽음"
                    imgDetailHangoverStatusSangque.isSelected = false
                    imgDetailHangoverStatusEueuk.isSelected = false
                    imgDetailHangoverStatusDeath.isSelected = true
                }
            }
        }
        detailData.hanoverStatus = "상쾌"
        imgDetailHangoverStatusSangque.setOnClickListener(statusClickListener)
        imgDetailHangoverStatusSangque.isSelected = true
        imgDetailHangoverStatusEueuk.setOnClickListener(statusClickListener)
        imgDetailHangoverStatusDeath.setOnClickListener(statusClickListener)
    }

    private fun initAlcoholImageView() {
        imgDetailSoju.setOnClickListener {
            recordDetailRecyclerViewAdapter.addAlcohol(Soju())
        }
        imgDetailBeer.setOnClickListener {
            recordDetailRecyclerViewAdapter.addAlcohol(Beer())
        }
        imgDetailWine.setOnClickListener {
            recordDetailRecyclerViewAdapter.addAlcohol(Wine())
        }
        imgDetailRiceWine.setOnClickListener {
            recordDetailRecyclerViewAdapter.addAlcohol(RiceWine())
        }
        imgDetailLiquor.setOnClickListener {
            recordDetailRecyclerViewAdapter.addAlcohol(Liquor())
        }
        imgDetailEtc.setOnClickListener {
            recordDetailRecyclerViewAdapter.addAlcohol(AlcoholEtc())
        }

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        recyclerViewDetailAlcohol.layoutParams = params
    }


    companion object {
        private lateinit var recordDetailFragment: RecordDetailFragment

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