package com.example.f1app_v1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.f1app_v1.core.Result
import com.example.f1app_v1.data.model.Driver
import com.example.f1app_v1.repository.Driver.DriverRepository
import com.example.f1app_v1.repository.Season.SeasonRepository
import kotlinx.coroutines.Dispatchers

class DriverViewModel(
    private val seasonRepo: SeasonRepository,
    private val repo: DriverRepository
) : ViewModel() {

    fun fetchDriversBaseInfo() = liveData(Dispatchers.IO)
    {
        emit(Result.Loading())
        //val start = System.currentTimeMillis()
        var id = seasonRepo.getSeasonIds().stages[0].id //Position 0 corresponds to current season
        id = id.replace(":", "%3a")
        Thread.sleep(800)
        //Log.d("tiempo","${System.currentTimeMillis()-start}")
        val driverBaseInfo = repo.getDriverBaseInfo(id)
        emit(Result.Success(driverBaseInfo))
    }

    fun fetchDriver(id: String) = liveData(Dispatchers.IO) {
        emit((repo.getDriver(id)))
    }
}

class DriverViewModelFactory(
    private val seasonRepo: SeasonRepository,
    private val repo: DriverRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SeasonRepository::class.java, DriverRepository::class.java)
            .newInstance(seasonRepo, repo)
    }
}