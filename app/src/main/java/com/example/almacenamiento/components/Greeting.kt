package com.example.almacenamiento.components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

private const val TAG = "Greeting"

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Log.i(TAG, "Greeting llamado con nombre: $name")
    Text(text = "Hello, $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    Greeting("Android")
}