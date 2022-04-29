package com.example.f1app_v1.data.remote

import com.example.f1app_v1.WebService
import com.example.f1app_v1.application.AppConstants

class TeamDataSource(private val webService: WebService) {
    suspend fun getTeamBaseInfo(id:String)=webService.getTeamBaseInfo(id, AppConstants.API_KEY)
    suspend fun getTeam(id:String)=webService.getTeam(id, AppConstants.API_KEY)

}