package com.example.f1app_v1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.f1app_v1.repository.Driver.DriverRepository
import com.example.f1app_v1.repository.Race.RaceRepository
import kotlinx.coroutines.Dispatchers


class RaceViewModel(private val repo: RaceRepository):ViewModel() {

    fun fetchRaceBaseInfo()=liveData(Dispatchers.IO){
        var id = repo.getSeasonIds().stages[0].id //Position 0 corresponds to current season
        id=id.replace(":", "%3a")
        Thread.sleep(800)
        emit(repo.getRaceBaseInfo(id))

    }
}


class RaceViewModelFactory(private val repo: RaceRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RaceRepository::class.java).newInstance(repo)
    }
}