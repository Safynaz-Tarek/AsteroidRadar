package com.udacity.asteroidradar.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.api.getDays
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.model.asDatabasesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.await

enum class AsteroidFilter{WEEK, TODAY, FAVORITE}
class AsteroidRepository(private val database: AsteroidDatabase) {

    //    This is the property that everyone can use to observe asteroids from the repo
    val asteroids: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDBDao.getAllData()){
        it.asDomainModel()
    }

    val pictureOfDay: LiveData<PictureOfDay> = database.asteroidDBDao.getPictureOfTheDayLiveData()

    suspend fun refreshData(){
        withContext(Dispatchers.IO){
            try{
                val responseList = Network.retrofitService.getAsteroids()
                val asteroidList = parseAsteroidsJsonResult(JSONObject(responseList))
                database.asteroidDBDao.insertALl(*asteroidList.asDatabasesModel())
            }catch (e: Exception){
                Log.i("Repo", "Can't access data")
            }
        }
    }

    suspend fun deleteData(){
        withContext(Dispatchers.IO){
            database.asteroidDBDao.deletePrevAsteroids(getDays.today)
        }
    }

    suspend fun getPictureOfDay(){
        withContext(Dispatchers.IO){
            try{
                val responsePicture = Network.retrofitService.getPictureOfTheDays()
                database.asteroidDBDao.insertPicture(responsePicture)
            }catch (e: Exception){
                Log.i("Repo", "Can't access Picture")
            }
        }
    }

    suspend fun getAsteroidFiltered(filter: AsteroidFilter) : List<Asteroid>{
        var filteredResult: List<Asteroid> = listOf()
        withContext(Dispatchers.IO){
            filteredResult = when(filter){
                AsteroidFilter.TODAY -> database.asteroidDBDao.getTodayData(getDays.today).asDomainModel()
                AsteroidFilter.WEEK -> asteroids.value!!
                AsteroidFilter.FAVORITE -> database.asteroidDBDao.getTodayData(getDays.today).asDomainModel()
            }
        }
        return filteredResult
    }
}