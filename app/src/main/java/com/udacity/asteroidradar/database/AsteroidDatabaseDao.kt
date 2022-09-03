package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface AsteroidDatabaseDao {
    @Query("SELECT * from asteroid_data_table WHERE asteroidId = :key")
    fun get(key: Long): AsteroidData?

    @Query("SELECT * from asteroid_data_table")
    fun getAllData(): LiveData<List<AsteroidData>>
}