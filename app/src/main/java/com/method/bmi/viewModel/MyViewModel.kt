package com.method.bmi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.method.bmi.model.User

class MyViewModel: ViewModel() {

    companion object{
        lateinit var user: User
    }

    fun getUser(): User {
        return user
    }

    fun setUser(weight: Float, height: Float, gender: Int, athlete: Boolean){
        user = User(weight = weight, height = height, gender = gender, athlete = athlete)
    }

}