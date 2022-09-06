package com.udacity.asteroidradar.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.Service
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.asDatabasesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.await

class AsteroidRepository(private val database: AsteroidDatabase) {

    //    This is the property that everyone can use to observe asteroids from the repo
    val asteroids: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDBDao.getAllData()){
        it.asDomainModel()
    }

    suspend fun refreshData(){
        withContext(Dispatchers.IO){
            try{
            val responseList = Service.Network.retrofitService.getAsteroids()
            val asteroidList = parseAsteroidsJsonResult(JSONObject(responseList))
            database.asteroidDBDao.insertALl(*asteroidList.asDatabasesModel())
            }catch (e: Exception){
                Log.i("Repo", "Can't access data")
            }
        }
    }

    suspend fun deleteData(){
        withContext(Dispatchers.IO){
            database.asteroidDBDao.deletePrevAsteroids(Service.getDays.today)
        }
    }
}