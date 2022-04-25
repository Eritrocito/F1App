package com.example.f1app_v1.repository.Driver

import com.example.f1app_v1.data.remote.DriverDataSource

class DriverRepositoryImpl(private val driverDataSource:DriverDataSource): DriverRepository {

    override suspend fun getDriverBaseInfo(id: String) = driverDataSource.getDriverBaseInfo(id)
    override suspend fun getDriver(id: String) = driverDataSource.getDriver(id)

}