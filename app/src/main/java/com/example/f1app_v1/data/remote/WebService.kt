package com.example.f1app_v1

import com.example.f1app_v1.application.AppConstants
import com.example.f1app_v1.data.model.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {


    @GET("seasons.json")
    suspend fun getSeasonIds(@Query("api_key") apiKey: String): SeasonIdList

    @GET("sport_events/{id}/summary.json")
    suspend fun getDriverBaseInfo(@Path("id", encoded = true) id:String, @Query("api_key") apiKey: String): DriverBaseInfo

    @GET("competitors/{id}/profile.json")
    suspend fun getDriver(@Path("id", encoded = true) id:String, @Query("api_key") apiKey: String): Driver

    @GET("sport_events/{id}/summary.json")
    suspend fun getRaceBaseInfo(@Path("id",encoded=true) id:String, @Query("api_key") apiKey: String):RaceBaseInfo
}

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) //Para convertir la info de JSON a Movie
            .build().create(WebService::class.java)
    }
}