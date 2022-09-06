package com.udacity.asteroidradar.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "picture_of_day_table")
data class PictureOfDay(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,

    @PrimaryKey
    val url: String)