package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.Repository.AsteroidRepository
import com.udacity.asteroidradar.database.getDatabase
import retrofit2.HttpException

class RefreshAndDeleteDataWorkers(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params){

    companion object{
        const val WORK_NAME = "DeleteRefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidRepository(database)
        return try{
            repository.deleteData()
            repository.refreshData()
            Result.success()
        }catch (e: HttpException){
            Result.retry()
        }
    }
}

