package com.example.f1app_v1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.f1app_v1.repository.DriverRepository
import kotlinx.coroutines.Dispatchers

class DriverViewModel(private val repo:DriverRepository):ViewModel() {

    fun fetchDrivers() = liveData(Dispatchers.IO)
    {
        var id = repo.getSeasonIds().stages[0].id //Position 0 corresponds to current season
        id=id.replace(":", "%3a")
        emit(repo.getDriverIds(id))
    }
}

class DriverViewModelFactory(private val repo: DriverRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DriverRepository::class.java).newInstance(repo)
    }
}