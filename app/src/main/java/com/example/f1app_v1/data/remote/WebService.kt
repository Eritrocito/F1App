package com.example.f1app_v1

import com.example.f1app_v1.application.AppConstants
import com.example.f1app_v1.data.model.Season
import com.example.f1app_v1.data.model.SeasonIdList
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
    suspend fun getDriverIds(@Path("id", encoded = true) id:String, @Query("api_key") apiKey: String): Season

    //@GET("competitors/sr%3acompetitor%3a7135/profile.json")
    //suspend fun getCompetitors(@Query("api_key") apiKey: String):Competitor
}

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) //Para convertir la info de JSON a Movie
            .build().create(WebService::class.java)
    }
}