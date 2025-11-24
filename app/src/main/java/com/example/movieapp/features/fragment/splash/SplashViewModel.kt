package com.example.movieapp.features.fragment.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val nav_ :  MutableLiveData<Boolean> =  MutableLiveData(false)
    val nav : LiveData<Boolean> get() = nav_




    fun start(){
        viewModelScope.launch{
            delay(2000)
            nav_.postValue(true)

        }
    }

    fun stop() {
        nav_.value = false
    }


}