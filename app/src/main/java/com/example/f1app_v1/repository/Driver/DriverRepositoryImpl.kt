package com.example.f1app_v1.repository.Driver

import com.example.f1app_v1.data.remote.DataSource

class DriverRepositoryImpl(private val dataSource:DataSource): DriverRepository {
    override suspend fun getSeasonIds() = dataSource.getSeasonIds()
    override suspend fun getDriverIds(id: String) = dataSource.getDriverIds(id)
    override suspend fun getDriver(id: String) = dataSource.getDriver(id)

}