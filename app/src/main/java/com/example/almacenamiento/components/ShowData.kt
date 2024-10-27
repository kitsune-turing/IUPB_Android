package com.example.almacenamiento.components


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


private const val TAG = "ShowData"

@Composable
fun ShowData(data: List<Pair<String, Float>>) {
    Log.i(TAG, "Mostrando ${data.size} registros en la interfaz")

    data.forEachIndexed { index, (fecha, co2) ->
        Log.d(TAG, "Registro[$index]: Fecha = $fecha, CO2 = $co2 ppm")
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Datos de CO2", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            if (data.isEmpty()) {
                Log.w(TAG, "No hay datos disponibles para mostrar")
                Text(text = "No hay datos disponibles.")
            } else {
                data.forEach { (fecha, co2) ->
                    Text(text = "Fecha: $fecha - CO2: $co2 ppm")
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

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