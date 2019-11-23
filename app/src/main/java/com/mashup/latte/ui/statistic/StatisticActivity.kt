package com.mashup.latte.ui.statistic

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.mashup.latte.R
import kotlinx.android.synthetic.main.activity_statistic.*

class StatisticActivity : AppCompatActivity() {

    private lateinit var set: BarDataSet
    private val xAxisLabel = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        setData()
        highlightCalender()
        drawBarChart()
    }

    private fun highlightCalender() {

    }

    private fun drawBarChart() {
        val data = BarData(set)
        val xAxis = statistic_barchart.xAxis
        val rightAxis = statistic_barchart.axisRight
        val leftAxis = statistic_barchart.axisLeft

        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.isGranularityEnabled = true
        xAxis.textSize = 12f

        statistic_barchart.setXAxisRenderer(
            MyXAxisRenderer(
                statistic_barchart.viewPortHandler,
                statistic_barchart.xAxis,
                statistic_barchart.getTransformer(
                    YAxis.AxisDependency.LEFT
                )
            )
        )

        statistic_barchart.xAxis.valueFormatter = MyValueFormatter(xAxisLabel)
        set.valueFormatter = MyValueFormatter(xAxisLabel)
        set.valueTextSize = 12f
        set.highLightAlpha = 0
        set.setColors(
            intArrayOf(
                R.color.colorYellow,
                R.color.colorBackground,
                R.color.highlightDate,
                R.color.colorPrimary
            ), this
        )

        rightAxis.setDrawLabels(false)
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawAxisLine(false)

        leftAxis.setDrawLabels(false)
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(false)

        data.barWidth = 0.2f
        statistic_barchart.data = data
        statistic_barchart.setFitBars(true)
        statistic_barchart.setScaleEnabled(false)
        statistic_barchart.isDoubleTapToZoomEnabled = false
        statistic_barchart.setBackgroundColor(Color.rgb(255, 255, 255))
        statistic_barchart.animateXY(2000, 2000)
        statistic_barchart.description.text = ""
        statistic_barchart.extraBottomOffset = 30f
        statistic_barchart.legend.isEnabled = false
        statistic_barchart.setDrawBorders(false)
        statistic_barchart.invalidate()
    }

    //TODO 잔 수로 내림차순 정렬한 후 상위 다섯개 차트로 출력
    private fun setData() {
        val entries = arrayListOf<BarEntry>()
        entries.add(BarEntry(0f, 8f))
        entries.add(BarEntry(1f, 6f))
        entries.add(BarEntry(2f, 3f))
        entries.add(BarEntry(3f, 2f))

        xAxisLabel.add("스타벅스\n아메리카노")
        xAxisLabel.add("핫식스\n자몽")
        xAxisLabel.add("맙렙\n카페라떼")
        xAxisLabel.add("박카스\n")

        set = BarDataSet(entries, "CaffeineDataSet")
    }
}