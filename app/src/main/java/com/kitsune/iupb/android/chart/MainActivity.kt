package com.kitsune.iupb.android.chart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.kitsune.iupb.android.chart.data.ChartUtils
import com.kitsune.iupb.android.chart.data.CsvReader

class MainActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart
    private lateinit var csvReader: CsvReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barChart = findViewById(R.id.bar_chart)
        pieChart = findViewById(R.id.pie_chart)

        csvReader = CsvReader(this)


        val top10Data = csvReader.getTop10Municipalities()
        ChartUtils.setupBarChart(barChart, top10Data)
        ChartUtils.setupPieChart(pieChart, top10Data)
    }
}