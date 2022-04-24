package com.example.f1app_v1.repository.Driver

import com.example.f1app_v1.data.model.*

interface DriverRepository {
    suspend fun getDriverBaseInfo(id:String): DriverBaseInfo
    suspend fun getDriver(id:String):Driver

}