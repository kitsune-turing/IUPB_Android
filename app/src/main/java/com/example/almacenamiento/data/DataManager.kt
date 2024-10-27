package com.example.almacenamiento.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * DataManager is a singleton object responsible for managing the
 * CO2 data storage and retrieval using SharedPreferences.
 */
object DataManager {
    private const val PREFS_NAME = "CO2_DATA"

    
    /**
     * Saves CO2 data from a file located in the assets directory to SharedPreferences.
     *
     * @param context The application context used to access SharedPreferences and assets.
     */
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
                            Log.i("DataManager_SaveData", "Saving: Date = $date, CO2 = $co2Value")
                        } else {
                            Log.e("DataManager_Error", "Invalid CO2 value in: $line")
                        }
                    } else {
                        Log.w("DataManager_Warning", "Incorrect format in line: $line")
                    }
                }
            }
            editor.apply()
            Log.i("DataManager_SaveData", "Data successfully saved to SharedPreferences")

        } catch (e: Exception) {
            Log.e("DataManager_Error", "Error reading file: ${e.message}")
        }
    }

    /**
     * Retrieves CO2 data from SharedPreferences.
     *
     * @param context The application context used to access SharedPreferences.
     * @return A list of pairs containing date and CO2 values.
     */
    fun getDataSharedPreferences(context: Context): List<Pair<String, Float>> {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val data = mutableListOf<Pair<String, Float>>()

        var index = 1
        while (true) {
            val date = sharedPreferences.getString("date_$index", null)
            val co2 = sharedPreferences.getFloat("co2_$index", Float.MIN_VALUE)

            if (date == null) {
                Log.w("DataManager_Warning", "No more information found in SharedPreferences.")
                break
            }

            Log.d("DataManager_GetData", "Retrieving: Date = $date, CO2 = $co2")
            data.add(date to co2)
            index++
        }
        Log.i("DataManager_GetData", "Total records retrieved: ${data.size}")
        return data
    }
}
