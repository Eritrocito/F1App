package com.example.f1app_v1.repository

import com.example.f1app_v1.data.model.Season
import com.example.f1app_v1.data.model.SeasonIdList
import com.example.f1app_v1.data.remote.DataSource

class DriverRepositoryImpl(private val dataSource:DataSource):DriverRepository {
    override suspend fun getSeasonIds(): SeasonIdList = dataSource.getSeasonIds()
    override suspend fun getDriverIds(id: String): Season = dataSource.getDriverIds(id)

}