package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay

@Dao
interface AsteroidDatabaseDao {

    @Query("SELECT * from databaseasteroids ORDER BY closeApproachDate DESC")
    fun getAllData(): LiveData<List<DatabaseAsteroids>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertALl(vararg asteroids: DatabaseAsteroids )

    @Query("SELECT * from databaseasteroids WHERE closeApproachDate < :day ")
    fun deletePrevAsteroids(day :String): Int

    @Query("SELECT * from databaseasteroids WHERE closeApproachDate == :day ORDER BY closeApproachDate Desc")
    fun getTodayData(day: String): List<DatabaseAsteroids>

    @Query("SELECT * FROM picture_of_day_table")
    fun getPictureOfTheDayLiveData(): LiveData<PictureOfDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicture(picture: PictureOfDay)

}


@Database(entities = [DatabaseAsteroids::class, PictureOfDay::class], version = 1, exportSchema = false)
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
