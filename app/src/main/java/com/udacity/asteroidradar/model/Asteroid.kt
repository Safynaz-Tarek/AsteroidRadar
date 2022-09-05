package com.udacity.asteroidradar.model

import android.os.Parcelable
import androidx.lifecycle.Transformations.map
import com.udacity.asteroidradar.database.DatabaseAsteroids
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(val id: Long, val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable

fun List<Asteroid>.asDatabasesModel(): Array<DatabaseAsteroids>{
    return map{
        DatabaseAsteroids(
            id = it.id,
            codename = it.codename,
            isPotentiallyHazardous = it.isPotentiallyHazardous,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth
        )
    }.toTypedArray()
}
