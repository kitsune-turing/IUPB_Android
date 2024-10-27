package com.example.almacenamiento.components


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * Composable function that displays CO2 data in a structured layout.
 *
 * @param data A list of pairs where each pair contains a date (String) and a CO2 value (Float).
 */
@Composable
fun ShowData(data: List<Pair<String, Float>>) {
    Log.i("ShowData_Display", "Displaying ${data.size} records in the UI")

    // Log each record for debugging
    data.forEachIndexed { index, (fecha, co2) ->
        Log.d("ShowData_Display", "Record[$index]: Date = $fecha, CO2 = $co2 ppm")
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "CO2 Data", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            if (data.isEmpty()) {
                Log.w("ShowData_No_Content_Data", "No data available to display")
                Text(text = "No data available.")
            } else {
                data.forEach { (fecha, co2) ->
                    Text(text = "Date: $fecha - CO2: $co2 ppm")
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}


/**
 * Preview function for the ShowData composable.
 */
@Preview(showBackground = true)
@Composable
fun PreviewShowData() {
    ShowData(
        listOf(
            "2024-10-20" to 400.5f,
            "2024-10-21" to 405.3f
        )
    )
}
