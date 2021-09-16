package com.method.bmi.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.method.bmi.R
import com.method.bmi.databinding.ResultBinding
import com.method.bmi.model.DataAnalyzer
import com.method.bmi.model.User
import com.method.bmi.viewModel.MyViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class Result: Fragment() {

    private var _binding: ResultBinding? = null
    private val binding get() = _binding!!

    private val viewModel = MyViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImages()
        setData()
    }

    private fun setData() {
        val data = DataAnalyzer()
        binding.bmiTxt.text = BigDecimal(data.bmiCalculator().toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()
        binding.weightTxt.text = BigDecimal(data.weightCalculator().toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()
        binding.calorieTxt.text = BigDecimal(data.calorieCalculator().toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.currentWeight.text = getString(R.string.current_weight) + viewModel.getUser().weight.toString()
        binding.currentHeight.text = getString(R.string.current_height) + viewModel.getUser().height.toString()

        bmiColor(data.bmiCalculator())
    }

    private fun bmiColor(bmi: Float) {
        if (bmi < 18.5){
            binding.bmiTxt.setTextColor(Color.parseColor("#BCA907"))
        } else if (bmi > 18.5 && bmi <= 25){
            binding.bmiTxt.setTextColor(Color.parseColor("#047108"))
        }else if (bmi > 25 && bmi <= 30){
            binding.bmiTxt.setTextColor(Color.parseColor("#BCA907"))
        }else if (bmi > 30){
            binding.bmiTxt.setTextColor(Color.parseColor("#BA180C"))
        }
    }

    private fun setImages() {
        val user = viewModel.getUser()

        if (user.gender == 1){
            binding.avatar.setImageResource(R.drawable.male)
        }else if (user.gender == 2){
            binding.avatar.setImageResource(R.drawable.female)
        }

        if (user.athlete){
            binding.athlete.visibility = View.VISIBLE
        }else{
            binding.athlete.visibility = View.INVISIBLE
        }
    }
}