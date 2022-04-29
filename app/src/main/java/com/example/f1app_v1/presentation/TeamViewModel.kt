package com.example.f1app_v1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.f1app_v1.core.Result
import com.example.f1app_v1.repository.Driver.DriverRepository
import com.example.f1app_v1.repository.Season.SeasonRepository
import com.example.f1app_v1.repository.Team.TeamRepository
import kotlinx.coroutines.Dispatchers

class TeamViewModel(
    private val seasonRepo: SeasonRepository,
    private val repo: TeamRepository
) : ViewModel() {

    fun fetchTeamsBaseInfo() = liveData(Dispatchers.IO)
    {
        emit(Result.Loading())
        var id = seasonRepo.getSeasonIds().stages[0].id //Position 0 corresponds to current season
        id = id.replace(":", "%3a")
        Thread.sleep(800)
        val teamBaseInfo = repo.getTeamBaseInfo(id)
        emit(Result.Success(teamBaseInfo))
    }
    fun fetchTeam(id:String) = liveData(Dispatchers.IO){
        emit(repo.getTeam(id))
    }
}

class TeamViewModelFactory(
    private val seasonRepo: SeasonRepository,
    private val repo: TeamRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SeasonRepository::class.java, TeamRepository::class.java)
            .newInstance(seasonRepo, repo)
    }
}