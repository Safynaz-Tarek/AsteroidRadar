package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid_data_table")
class AsteroidData (
    @PrimaryKey
    var asteroidId: Long = 0L,

    @ColumnInfo(name = "absolute_magnitude")
    var absoluteMagnitude : Double = 1.0,

    @ColumnInfo(name = "estimated_diameter")
    var estimatedDiameter: Double = 1.0,

    @ColumnInfo(name = "is_hazerd")
    var isHazerd : Boolean = false,

    @ColumnInfo(name = "approach_date")
    var approachDate: String = "",

    @ColumnInfo(name = "relative_velocity")
    var relativeVelocity: Double = 1.0,

    @ColumnInfo(name = "distance_from_earth")
    var distanceFromEarth: Double = 1.0


)