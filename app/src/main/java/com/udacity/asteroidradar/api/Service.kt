package com.udacity.asteroidradar.api

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


object getDays{
    val nextSevenDays: List<String>
        get() = getNextSevenDaysFormattedDates()

    val today: String
        get() = nextSevenDays[0]

    val todayPlusSevenDays: String
        get() = nextSevenDays[6]
}


interface AsteroidService{
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate:String = getDays.today,
        @Query("end_date") endDate: String = getDays.todayPlusSevenDays,
        @Query("api_key") apiKey: String = Constants.API_KEY):
            String

    @GET("planetary/apod")
    suspend fun getPictureOfTheDays(@Query("api_key") apiKey: String = Constants.API_KEY):
            PictureOfDay
}

object Network{
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

    val retrofitService = retrofit.create(AsteroidService::class.java)
}

