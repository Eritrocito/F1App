package com.example.f1app_v1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.f1app_v1.data.model.Driver
import com.example.f1app_v1.repository.DriverRepository
import kotlinx.coroutines.Dispatchers

class DriverViewModel(private val repo:DriverRepository):ViewModel() {

    fun fetchDrivers() = liveData(Dispatchers.IO)
    {
        var id = repo.getSeasonIds().stages[0].id //Position 0 corresponds to current season
        id=id.replace(":", "%3a")
        val driverIds=repo.getDriverIds(id)
        var DriverList=mutableListOf<Driver>()
        var driverId:String=""//driverIds.stage.competitors[0].id
        //driverId=driverId.replace(":", "%3a")
        //emit(repo.getDriver(driverId))
        var cont=0
        for(item in driverIds.stage.competitors){
            driverId=item.id
            driverId=driverId.replace(":", "%3a")
            DriverList.add(repo.getDriver(driverId))
            cont=cont+1
        }
        emit(DriverList)
    }
}

class DriverViewModelFactory(private val repo: DriverRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DriverRepository::class.java).newInstance(repo)
    }
}