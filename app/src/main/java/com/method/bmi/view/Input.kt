package com.method.bmi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.method.bmi.R
import com.method.bmi.databinding.InputBinding
import com.method.bmi.viewModel.MyViewModel

class Input: Fragment() {

    private var _binding: InputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickResult()
    }

    private fun clickResult() {
        binding.resultBtn.setOnClickListener{

            if (checkValidation()){

                val viewModel = MyViewModel()
                viewModel.setUser(
                    height = binding.heightEdittext.text.toString().toFloat(),
                    weight = binding.weightEdittext.text.toString().toFloat(),
                    gender = getGender(),
                    athlete = binding.athleteCheckbox.isChecked)

                findNavController().navigate(R.id.action_input_to_result)
            }
        }
    }

    private fun getGender(): Int {
        return if (binding.maleRadioButton.isChecked){
            1
        }else{
            2
        }
    }

    private fun checkValidation(): Boolean {
        if (binding.heightEdittext.text.toString() == "" || binding.weightEdittext.text.toString() == ""){
            Toast.makeText(context, getString(R.string.fill_blanks), Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.maleRadioButton.isChecked && binding.femaleRadioButton.isChecked){
            Toast.makeText(context, getString(R.string.choose_gender), Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}