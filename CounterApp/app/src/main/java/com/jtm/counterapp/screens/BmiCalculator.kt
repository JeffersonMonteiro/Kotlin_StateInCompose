package com.jtm.counterapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCalculatorPage(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "BMI Calculator")
                }
            )
        }
    ) {
        val weight = remember { mutableStateOf("") }
        val height = remember { mutableStateOf("") }
        val bmi = remember { mutableStateOf("") }
        val status = remember { mutableStateOf("") }

        Column(
            modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditNumberField(
                value = weight.value,
                label = "Weight in (Kg)",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                    weight.value = it
                }
            )

            EditNumberField(
                value = height.value,
                label = "Height in Meter",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChanged = {
                    height.value = it
                }
            )

            Button(
                onClick = {
                    bmi.value = calculateBmi(
                        weight.value.toDoubleOrNull() ?: 0.0,
                        height.value.toDoubleOrNull() ?: 0.0
                    )
                    status.value = getBMIStatus(bmi.value.toDoubleOrNull() ?: 0.0)
                }
            ) {
                Text(text = "Calculate")
            }
            BmiResult(bmi = bmi.value, status = status.value)
        }
    }
}

@SuppressLint("DefaultLocale")
fun calculateBmi(weight: Double, height: Double): String {
    val bmi = weight / (height * height)
    return String.format("%.1f", bmi)
}

fun getBMIStatus(bmi: Double): String {
    return when (bmi) {
        in Double.NEGATIVE_INFINITY..15.9 -> BMIStatus.UNDERWEIGHT_SEVERE
        in 16.0..16.9 -> BMIStatus.UNDERWEIGHT_MODERATE
        in 17.0..18.4 -> BMIStatus.UNDERWEIGHT
        in 18.5..24.9 -> BMIStatus.NORMAL
        in 25.0..29.9 -> BMIStatus.OVERWEIGHT
        in 30.0..34.9 -> BMIStatus.OBESE_CLASS_I
        in 35.0..39.9 -> BMIStatus.OBESE_CLASS_II
        in 40.0..Double.MAX_VALUE -> BMIStatus.OBESE_CLASS_III
        else -> BMIStatus.OBESE_CLASS_III
    }
}

@Composable
fun EditNumberField(
    value: String,
    label: String,
    keyboardOptions: KeyboardOptions,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        label = {
            Text(text = label)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    )

}

@Composable
fun BmiResult(
    bmi: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Your BMI: $bmi",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        for (key in statusMap.keys) {
          Row(
              modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                  .fillMaxWidth()
          ){
              Text(
                  text = key,
                  modifier = Modifier.weight(1f)
              )
              Text(
                  text = statusMap[key]!!)
          }
        }

    }
}

@Preview
@Composable
private fun BmiCalculatorPagePreview() {
    BmiCalculatorPage()
}

object BMIStatus {
    const val UNDERWEIGHT_SEVERE = "Underweight (Severe Thinness)"
    const val UNDERWEIGHT_MODERATE = "Underweight (Moderate Thinness)"
    const val UNDERWEIGHT = "Underweight (Mild Thinness)"
    const val NORMAL = "Normal"
    const val OVERWEIGHT = "Overweight (Pre-obese)"
    const val OBESE_CLASS_I = "Obese (Class I)"
    const val OBESE_CLASS_II = "Obese (Class II)"
    const val OBESE_CLASS_III = "Obese (Class III)"
}

val statusMap = mapOf(
    BMIStatus.UNDERWEIGHT_SEVERE to "Less than 16.0",
    BMIStatus.UNDERWEIGHT_MODERATE to "16.0 - 16.9",
    BMIStatus.UNDERWEIGHT to "17.0 - 18.4",
    BMIStatus.NORMAL to "18.5 - 24.9",
    BMIStatus.OVERWEIGHT to "25.0 - 29.9",
    BMIStatus.OBESE_CLASS_I to "30.0 - 34.9",
    BMIStatus.OBESE_CLASS_II to "35.0 - 39.9",
    BMIStatus.OBESE_CLASS_III to "40.0 and above",
)


