package com.jtm.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jtm.counterapp.screens.BmiCalculatorPage
import com.jtm.counterapp.screens.CounterApp
import com.jtm.counterapp.screens.PhotoGalleryScreen
import com.jtm.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
               MyApp()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    //CounterApp()
    //PhotoGalleryScreen()
    BmiCalculatorPage()
}

