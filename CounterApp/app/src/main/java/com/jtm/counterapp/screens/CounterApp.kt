package com.jtm.counterapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//Initial Composition --> When I starts the application
//Recomposition --> Always take action to every change of the variable
@Composable
fun CounterApp(modifier: Modifier = Modifier) {
    var counter : MutableState<Int> = remember {
        mutableStateOf(0)
    }
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Log.d("CounterApp", "CounterApp: recompose")
        Text(text = "You Have pushed the button this many times")
        Text(text = "${counter.value}", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            Log.d("CounterApp", "CounterApp: ${counter.value}")
            counter.value++
        }) {
            Text(text = "Push Me")
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun CounterAppPreview() {
    CounterApp()
}