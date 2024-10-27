package com.example.almacenamiento.components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Composable function that displays a greeting message.
 *
 * @param name The name to include in the greeting message.
 * @param modifier Optional modifier for styling the Text composable.
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Log.i("Greeting_Execution", "Greeting called with name: $name")
    Text(text = "Hello, $name!", modifier = modifier)
}

/**
 * Preview function for the Greeting composable.
 */
@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    Greeting("Android")
}
