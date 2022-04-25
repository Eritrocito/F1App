package com.example.f1app_v1.repository.Season

import com.example.f1app_v1.data.model.SeasonIdList
import com.example.f1app_v1.data.remote.SeasonDataSource

class SeasonRepositoryImpl(private val seasonDataSource: SeasonDataSource):SeasonRepository {
    override suspend fun getSeasonIds()=seasonDataSource.getSeasonIds()
}