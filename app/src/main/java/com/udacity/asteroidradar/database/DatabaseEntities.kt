package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.model.Asteroid

@Entity
data class DatabaseAsteroids (
    @PrimaryKey
    var id: Long = 0L,
    var codename: String ="",
    var absoluteMagnitude : Double = 1.0,
    var estimatedDiameter: Double = 1.0,
    var isPotentiallyHazardous : Boolean = false,
    var closeApproachDate: String = "",
    var relativeVelocity: Double = 1.0,
    var distanceFromEarth: Double = 1.0

)

fun List<DatabaseAsteroids>.asDomainModel(): List<Asteroid>{
    return map{
        Asteroid(
            id = it.id,
            codename = it.codename,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            isPotentiallyHazardous = it.isPotentiallyHazardous,
            closeApproachDate = it.closeApproachDate,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth
        )
    }
}