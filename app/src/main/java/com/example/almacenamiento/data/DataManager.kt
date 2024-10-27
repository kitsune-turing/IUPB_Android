package com.example.almacenamiento.data


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader


object DataManager {
    private const val PREFS_NAME = "CO2_DATA"
    private val TAG = "DataManager"

    // Guarda los datos en SharedPreferences desde el archivo en assets.
    fun saveDataSharedPreferences(context: Context) {
        try {
            val inputStream = context.assets.open("co2_data.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            reader.useLines { lines ->
                lines.forEachIndexed { index, line ->
                    val parts = line.split(",").map { it.trim() }

                    if (parts.size == 2) {
                        val date = parts[0]
                        val co2Value = parts[1].toFloatOrNull()

                        if (co2Value != null) {
                            editor.putString("date_${index + 1}", date)
                            editor.putFloat("co2_${index + 1}", co2Value)
                            Log.i(TAG, "Guardando: Fecha = $date, CO2 = $co2Value")
                        } else {
                            Log.e(TAG, "Valor CO2 inválido en: $line")
                        }
                    } else {
                        Log.w(TAG, "Formato incorrecto en la línea: $line")
                    }
                }
            }
            editor.apply()
            Log.i(TAG, "Datos guardados exitosamente en SharedPreferences")

        } catch (e: Exception) {
            Log.e(TAG, "Error al leer el archivo: ${e.message}")
        }
    }

    // Recupera los datos desde SharedPreferences.
    fun getDataSharedPreferences(context: Context): List<Pair<String, Float>> {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val data = mutableListOf<Pair<String, Float>>()

        var index = 1
        while (true) {
            val date = sharedPreferences.getString("date_$index", null)
            val co2 = sharedPreferences.getFloat("co2_$index", Float.MIN_VALUE)

            if (date == null) {
                Log.w(TAG, "No se encontró más información en SharedPreferences.")
                break
            }

            Log.d(TAG, "Recuperando: Fecha = $date, CO2 = $co2")
            data.add(date to co2)
            index++
        }

        Log.i(TAG, "Total de registros recuperados: ${data.size}")
        return data
    }
}