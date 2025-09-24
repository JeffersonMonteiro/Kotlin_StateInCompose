package com.jtm.counterapp.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BmiCalculatorViewModel : ViewModel() {

    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var bmi by mutableStateOf("")
    var status by mutableStateOf("")

    private fun getBMIStatus(bmi: Double): String {
        return when (bmi) {
            in Double.NEGATIVE_INFINITY..15.9 -> UNDERWEIGHT_SEVERE
            in 16.0..16.9 -> UNDERWEIGHT_MODERATE
            in 17.0..18.4 -> UNDERWEIGHT
            in 18.5..24.9 -> NORMAL
            in 25.0..29.9 -> OVERWEIGHT
            in 30.0..34.9 -> OBESE_CLASS_I
            in 35.0..39.9 -> OBESE_CLASS_II
            in 40.0..Double.MAX_VALUE -> OBESE_CLASS_III
            else -> OBESE_CLASS_III
        }
    }

    @SuppressLint("DefaultLocale")
    fun calculateBmi() {
        val weight = weight.toDoubleOrNull() ?: 0.0
        val height = height.toDoubleOrNull() ?: 0.0
        val bmiDouble = weight / (height * height)
        bmi = String.format("%.1f", bmiDouble)
        status = getBMIStatus(bmiDouble)
    }


    companion object {
        const val UNDERWEIGHT_SEVERE = "Underweight (Severe Thinness)"
        const val UNDERWEIGHT_MODERATE = "Underweight (Moderate Thinness)"
        const val UNDERWEIGHT = "Underweight (Mild Thinness)"
        const val NORMAL = "Normal"
        const val OVERWEIGHT = "Overweight (Pre-obese)"
        const val OBESE_CLASS_I = "Obese (Class I)"
        const val OBESE_CLASS_II = "Obese (Class II)"
        const val OBESE_CLASS_III = "Obese (Class III)"

        val statusMap = mapOf(
            UNDERWEIGHT_SEVERE to "Less than 16.0",
            UNDERWEIGHT_MODERATE to "16.0 - 16.9",
            UNDERWEIGHT to "17.0 - 18.4",
            NORMAL to "18.5 - 24.9",
            OVERWEIGHT to "25.0 - 29.9",
            OBESE_CLASS_I to "30.0 - 34.9",
            OBESE_CLASS_II to "35.0 - 39.9",
            OBESE_CLASS_III to "40.0 and above",
        )
    }
}