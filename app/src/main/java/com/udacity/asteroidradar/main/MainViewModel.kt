package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.Repository.AsteroidFilter
import com.udacity.asteroidradar.Repository.AsteroidRepository
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

//    This part is for the refresh / repo

    private val database = getDatabase(application)
    private val asteroidsRepo = AsteroidRepository(database)
    var isFilterApplied = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            isFilterApplied.value = false
            asteroidsRepo.refreshData()
            asteroidsRepo.getPictureOfDay()

        }
    }


    lateinit var asteroidFilteredFeed :List<Asteroid>
    val asteroidFeed = asteroidsRepo.asteroids
    val pictureOfDay= asteroidsRepo.pictureOfDay

    //    This part is for applying filtering
    fun getFilteredAsteroids(filter: AsteroidFilter){
        viewModelScope.launch {
            asteroidFilteredFeed = asteroidsRepo.getAsteroidFiltered(filter)
            isFilterApplied.value = true

        }
    }

    fun doneFiltering(){
        isFilterApplied.value = false
    }

//    This part is to navigate to the detail screen
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