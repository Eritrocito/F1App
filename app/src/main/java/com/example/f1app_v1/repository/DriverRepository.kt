package com.example.f1app_v1.repository

import com.example.f1app_v1.data.model.Season
import com.example.f1app_v1.data.model.SeasonIdList

interface DriverRepository {
    suspend fun getSeasonIds():SeasonIdList
    suspend fun getDriverIds(id:String): Season
}