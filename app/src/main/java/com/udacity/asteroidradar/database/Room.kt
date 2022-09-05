package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.model.Asteroid

@Dao
interface AsteroidDatabaseDao {
    @Query("SELECT * from databaseasteroids WHERE id = :key")
    fun get(key: Long): Asteroid?

    @Query("SELECT * from databaseasteroids ORDER BY closeApproachDate DESC")
    fun getAllData(): LiveData<List<DatabaseAsteroids>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertALl(vararg asteroids: DatabaseAsteroids )
}

@Database(entities = [DatabaseAsteroids::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase() {
    abstract val asteroidDBDao: AsteroidDatabaseDao
}

private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase{
    synchronized(AsteroidDatabase::class.java){
        if(!:: INSTANCE.isInitialized){
            INSTANCE = androidx.room.Room.databaseBuilder(context.applicationContext,
                AsteroidDatabase::class.java,"Asteroids"
            ).build()
        }
    }
    return INSTANCE
}
