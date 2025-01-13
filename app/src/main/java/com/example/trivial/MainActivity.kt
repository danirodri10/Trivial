package com.example.trivial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.regalosnavidad.ui.theme.RegalosNavidadTheme
import com.example.trivial.ui.navigation.AppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegalosNavidadTheme {
                AppNavigation()
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun AppNavigationPreview(){
    AppNavigation()
}
