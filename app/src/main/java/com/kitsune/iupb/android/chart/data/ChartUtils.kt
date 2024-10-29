package com.kitsune.iupb.android.chart.data

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.graphics.Color
import android.util.Log

object ChartUtils {
    fun setupBarChart(barChart: BarChart, data: List<CsvReader.MunicipalityData>) {
        // Verify data
        if (data.isEmpty()) {
            Log.d("ChartUtils", "No hay datos para mostrar en el BarChart.")
            return
        }

        // Inputs configuration
        val entries = data.mapIndexed { index, item -> BarEntry(index.toFloat(), item.value) }
        val dataSet = BarDataSet(entries, "Top 10 Municipios").apply {
            color = Color.BLUE
        }
        val barData = BarData(dataSet)

        // Graphic configuration
        barChart.data = barData
        barChart.xAxis.labelRotationAngle = -45f
        barChart.description.isEnabled = false
        barChart.setFitBars(true)
        barChart.invalidate()
    }


    fun setupPieChart(pieChart: PieChart, data: List<CsvReader.MunicipalityData>) {
        if (data.isEmpty()) {
            Log.d("ChartUtils", "No hay datos para mostrar en el PieChart.")
            return
        }

        val entries = data.map { PieEntry(it.value, it.name) }
        val dataSet = PieDataSet(entries, "Distribución de Valores").apply {
            colors = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        }
        val pieData = PieData(dataSet)

        pieChart.data = pieData
        pieChart.invalidate()
    }
}