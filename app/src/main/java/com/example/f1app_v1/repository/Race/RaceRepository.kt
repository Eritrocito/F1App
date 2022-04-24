package com.example.f1app_v1.repository.Race

import com.example.f1app_v1.data.model.RaceBaseInfo
import com.example.f1app_v1.data.model.SeasonIdList

interface RaceRepository {
    //suspend fun getSeasonIds(): SeasonIdList
    suspend fun getRaceBaseInfo(id:String): RaceBaseInfo

}