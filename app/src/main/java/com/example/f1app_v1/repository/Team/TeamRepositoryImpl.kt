package com.example.f1app_v1.repository.Team

import com.example.f1app_v1.data.model.Team
import com.example.f1app_v1.data.model.TeamBaseInfo
import com.example.f1app_v1.data.remote.TeamDataSource

class TeamRepositoryImpl(private val teamDataSource: TeamDataSource) : TeamRepository {
    override suspend fun getTeamBaseInfo(id: String) = teamDataSource.getTeamBaseInfo(id)
    override suspend fun getTeam(id: String) = teamDataSource.getTeam(id)
}