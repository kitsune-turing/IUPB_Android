package com.example.almacenamiento


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.almacenamiento.ui.theme.AlmacenamientoTheme
import com.example.almacenamiento.components.ShowData
import com.example.almacenamiento.data.DataManager


class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: Iniciando MainActivity")

        // Guarda los datos en SharedPreferences desde el archivo.
        DataManager.saveDataSharedPreferences(this)

        // Configura la interfaz con Jetpack Compose.
        setContent {
            AlmacenamientoTheme {
                // Recupera los datos y los muestra en la pantalla.
                val co2Data = DataManager.getDataSharedPreferences(applicationContext)
                Log.i(TAG, "onCreate: Datos recuperados: ${co2Data.size} registros")

                co2Data.forEach { (fecha, co2) ->
                    Log.d(TAG, "Registro -> Fecha: $fecha - CO2: $co2 ppm")
                }

                ShowData(co2Data)
            }
        }
    }
}