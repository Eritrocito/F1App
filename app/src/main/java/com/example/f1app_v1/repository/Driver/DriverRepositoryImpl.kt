package com.example.f1app_v1.repository.Driver

import com.example.f1app_v1.data.remote.DataSource
import com.example.f1app_v1.data.remote.SeasonDataSource

class DriverRepositoryImpl(private val dataSource:DataSource): DriverRepository {

    override suspend fun getDriverBaseInfo(id: String) = dataSource.getDriverBaseInfo(id)
    override suspend fun getDriver(id: String) = dataSource.getDriver(id)

}