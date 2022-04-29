package com.example.f1app_v1.repository.Team

import com.example.f1app_v1.data.model.DriverBaseInfo
import com.example.f1app_v1.data.model.Team
import com.example.f1app_v1.data.model.TeamBaseInfo

interface TeamRepository {
    suspend fun getTeamBaseInfo(id:String): TeamBaseInfo
    suspend fun getTeam(id:String): Team
}