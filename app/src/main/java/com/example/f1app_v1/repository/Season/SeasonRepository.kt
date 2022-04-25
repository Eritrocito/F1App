package com.example.f1app_v1.repository.Season

import com.example.f1app_v1.data.model.SeasonIdList

interface SeasonRepository {
    suspend fun getSeasonIds(): SeasonIdList
}