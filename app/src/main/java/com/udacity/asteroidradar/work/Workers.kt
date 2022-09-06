package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.Repository.AsteroidRepository
import com.udacity.asteroidradar.database.getDatabase
import retrofit2.HttpException

class RefreshDataWorkers(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params){

    companion object{
        const val WORK_NAME = "RefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidRepository(database)
        return try{
            repository.refreshData()
            Result.success()
        }catch (e: HttpException){
            Result.retry()
        }
    }
}

class DeleteDataWorkers(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params){

    companion object{
        const val WORK_NAME = "DeleteDataWorker"
    }
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidRepository(database)
        return try{
            repository.deleteData()
            Result.success()
        }catch (e: HttpException){
            Result.retry()
        }
    }
}