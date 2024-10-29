package com.kitsune.iupb.android.chart.data


import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader


class CsvReader(private val context: Context) {
    data class MunicipalityData(val name: String, val value: Float)

    fun getTop10Municipalities(): List<MunicipalityData> {
        val inputStream = context.assets.open("metrics.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val municipalityMap = mutableMapOf<String, Float>()
        reader.readLine()

        reader.lineSequence().forEach { line ->
            val parts = line.split(";")

            if (parts.size >= 2) {
                val value = parts[0].trim().toFloatOrNull()
                val municipality = parts[1].trim()

                if (value != null) {
                    municipalityMap[municipality] = municipalityMap.getOrDefault(municipality, 0f) + value
                }
            }
        }

        reader.close()

        // Printer data
        Log.d("CsvReader", "Datos recogidos:")


        municipalityMap.entries.forEach { entry ->
            Log.d("CsvReader", "Municipio: ${entry.key}, Valor: ${entry.value}")
        }

        // Return ten municipalities
        return municipalityMap.entries
            .sortedByDescending { it.value }
            .take(10)
            .map { MunicipalityData(it.key, it.value) }
    }
}