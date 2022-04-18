package com.example.f1app_v1.data.remote

import com.example.f1app_v1.application.AppConstants
import com.example.f1app_v1.WebService
import com.example.f1app_v1.data.model.SeasonIdList

class DataSource(private val webService: WebService) {

    suspend fun getSeasonIds():SeasonIdList=webService.getSeasonIds(AppConstants.API_KEY)

    suspend fun getDriverIds(id:String)=webService.getDriverIds(id, AppConstants.API_KEY)

}