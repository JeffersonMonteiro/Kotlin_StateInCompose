package com.jtm.counterapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jtm.counterapp.R
import com.jtm.counterapp.viewmodels.BmiCalculatorViewModel
import com.jtm.counterapp.viewmodels.BmiCalculatorViewModel.Companion.statusMap

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
        val bmiCalculatorViewModel: BmiCalculatorViewModel = viewModel()

        Column(
            modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bmi_density),
                contentDescription = null,
                modifier = Modifier.weight(0.5f)
            )

            Spacer(modifier = Modifier.height(30.dp))
            EditNumberField(
                value = bmiCalculatorViewModel.weight,
                label = "Weight in (Kg)",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                    bmiCalculatorViewModel.weight = it
                }
            )

            EditNumberField(
                value = bmiCalculatorViewModel.height,
                label = "Height in Meter",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChanged = {
                    bmiCalculatorViewModel.height = it
                }
            )

            Button(
                onClick = {
                    bmiCalculatorViewModel.calculateBmi()
                }
            ) {
                Text(text = "Calculate")
            }
            BmiResult(
                bmi = bmiCalculatorViewModel.bmi,
                status = bmiCalculatorViewModel.status,
                modifier = Modifier.weight(1.5f),
                statusMap =  BmiCalculatorViewModel.statusMap
            )
        }
    }
}

@Composable
fun BmiResult(
    bmi: String,
    statusMap: Map<String, String>,
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

        if (status.isNotBlank()) {
            for (key in statusMap.keys) {
                val color = (if (status == key) Color.LightGray else Color.Transparent)
                val fontWeight = if (status == key) FontWeight.Bold else FontWeight.Normal

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .fillMaxWidth()
                        .background(color = color)
                ) {
                    Text(
                        text = key,
                        modifier = Modifier.weight(1f),
                        fontWeight = fontWeight
                    )
                    Text(
                        text = statusMap[key]!!, fontWeight = fontWeight
                    )
                }
            }
        }
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


@Preview
@Composable
private fun BmiCalculatorPagePreview() {
    BmiCalculatorPage()
}


