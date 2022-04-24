package com.example.f1app_v1.repository.Race

import com.example.f1app_v1.data.model.RaceBaseInfo
import com.example.f1app_v1.data.model.SeasonIdList
import com.example.f1app_v1.data.remote.RaceDataSource

class RaceRepositoryImpl(private val raceDataSource:RaceDataSource):RaceRepository {
    override suspend fun getSeasonIds()= raceDataSource.getSeasonIds()

    override suspend fun getRaceBaseInfo(id: String)= raceDataSource.getRaceBaseInfo(id)
}