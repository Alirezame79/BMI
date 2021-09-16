package com.method.bmi.model

import android.util.Log
import com.method.bmi.viewModel.MyViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class DataAnalyzer {

    private val viewModel = MyViewModel()
    private val normalWeightCode = 24.9

    companion object {
        private const val femaleGender = 0.95
        private const val maleGender = 1
        private const val normalFemale = 1.5
        private const val normalMale = 1.6
        private const val athleteFemale = 1.9
        private const val athleteMale = 2.1
    }

    fun bmiCalculator(): Float{
        val correctHeight = viewModel.getUser().height / 100
        val bmi = viewModel.getUser().weight / correctHeight.toDouble().pow(2.0)
        Log.d("TAF", "BMI is ${bmi.toFloat()}")
        return bmi.toFloat()
    }

    fun weightCalculator(): Float{
        val correctHeight = viewModel.getUser().height / 100
        val weight = correctHeight.toDouble().pow(2.0) * normalWeightCode
        return weight.toFloat()
    }

    fun calorieCalculator(): Float{
        var calorie = viewModel.getUser().weight * 24 * 1.1
        calorie *= if (viewModel.getUser().athlete){
            if (viewModel.getUser().gender == 1){
                maleGender * athleteMale
            }else{
                femaleGender * athleteFemale
            }
        }else{
            if (viewModel.getUser().gender == 1){
                maleGender * normalMale
            }else{
                femaleGender * normalFemale
            }
        }
        return calorie.toFloat()
    }
}