package com.example.almacenamiento

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.almacenamiento.ui.theme.AlmacenamientoTheme
import com.example.almacenamiento.components.ShowData
import com.example.almacenamiento.data.DataManager

/**
 * MainActivity is the entry point of the application.
 * It manages the main user interface and handles data storage and retrieval.
 */
class MainActivity : ComponentActivity() {

    /**
     * onCreate is called when the activity is first created.
     * It initializes the activity, sets up the user interface, 
     * and handles data management using SharedPreferences.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity_onCreateMain", "onCreate: Starting MainActivity")
        DataManager.saveDataSharedPreferences(this)

        // Set the user interface using Jetpack Compose
        setContent {
            AlmacenamientoTheme {
                val co2Data = DataManager.getDataSharedPreferences(applicationContext)

                Log.i("MainActivity_GetDatum_DataManager", "onCreate: Retrieved data: ${co2Data.size} records")
                co2Data.forEach { (date, co2) ->
                    Log.d("MainActivity_RegisterDatum_DataManager", "Record -> Date: $date - CO2: $co2 ppm")
                }
                ShowData(co2Data)
            }
        }
    }
}
