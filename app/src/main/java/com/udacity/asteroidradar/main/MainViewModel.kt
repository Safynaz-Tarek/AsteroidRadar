package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.model.Asteroid

class MainViewModel : ViewModel() {
    private val _navigateToDetailScreen = MutableLiveData<Asteroid?>()
    val navigateToDetailScreen
        get() = _navigateToDetailScreen

    fun onAsteroidClicked(asteroid: Asteroid){
        _navigateToDetailScreen.value = asteroid
    }

    fun onSetDetailNavigated(){
        _navigateToDetailScreen.value = null
    }
}