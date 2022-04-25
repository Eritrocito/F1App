package com.example.f1app_v1.data.remote

import com.example.f1app_v1.application.AppConstants
import com.example.f1app_v1.WebService
import com.example.f1app_v1.data.model.SeasonIdList

class DriverDataSource(private val webService: WebService) {

    suspend fun getDriverBaseInfo(id:String)=webService.getDriverBaseInfo(id, AppConstants.API_KEY)

    suspend fun getDriver(id:String)=webService.getDriver(id, AppConstants.API_KEY)

}