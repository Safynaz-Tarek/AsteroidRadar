package com.udacity.asteroidradar.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AsteroidData::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase() {

    abstract val asteroidDBDao: AsteroidDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: AsteroidDatabase? = null


    }
}