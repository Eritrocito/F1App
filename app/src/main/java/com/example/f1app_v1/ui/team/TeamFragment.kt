package com.example.f1app_v1.ui.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.core.Result
import com.example.f1app_v1.data.model.DriverBaseInfo
import com.example.f1app_v1.data.model.TeamBaseInfo
import com.example.f1app_v1.data.remote.DriverDataSource
import com.example.f1app_v1.data.remote.SeasonDataSource
import com.example.f1app_v1.data.remote.TeamDataSource
import com.example.f1app_v1.databinding.FragmentDriverBinding
import com.example.f1app_v1.databinding.FragmentTeamBinding
import com.example.f1app_v1.presentation.DriverViewModel
import com.example.f1app_v1.presentation.DriverViewModelFactory
import com.example.f1app_v1.presentation.TeamViewModel
import com.example.f1app_v1.presentation.TeamViewModelFactory
import com.example.f1app_v1.repository.Driver.DriverRepositoryImpl
import com.example.f1app_v1.repository.Season.SeasonRepositoryImpl
import com.example.f1app_v1.repository.Team.TeamRepositoryImpl
import com.example.f1app_v1.ui.Driver.DriverFragmentDirections
import com.example.f1app_v1.ui.adapters.DriverAdapter
import com.example.f1app_v1.ui.adapters.TeamAdapter

class TeamFragment : Fragment(R.layout.fragment_team), TeamAdapter.OnTeamClickListener {

    private val viewModel by viewModels<TeamViewModel> {
        TeamViewModelFactory(
            SeasonRepositoryImpl(SeasonDataSource(RetrofitClient.webservice)),
            TeamRepositoryImpl(TeamDataSource(RetrofitClient.webservice))
        )
    }

    private lateinit var binding: FragmentTeamBinding
    private lateinit var adapter: TeamAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamBinding.bind(view)

        viewModel.fetchTeamsBaseInfo().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter = TeamAdapter(result.data, this@TeamFragment)
                    binding.rvTeams.adapter = adapter
                }
            }


        })
    }


    override fun onTeamClick(team: TeamBaseInfo.Stage.Team) {
        var teamId = team.id
        teamId = teamId.replace(":", "%3a")
        viewModel.fetchTeam(teamId).observe(viewLifecycleOwner, Observer { team ->

            team.info?.let {
                val action = TeamFragmentDirections.actionTeamFragmentToTeamDetailFragment(
                    team.team.name, it.country_of_residence, it.debut,
                    it.foundation_year, team.competitors[0].name, team.competitors[0].nationality,
                    team.competitors[1].name, team.competitors[1].nationality
                )

                findNavController().navigate(action)
            } ?: Toast.makeText(
                this.context,
                "Team information not available right now",
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}