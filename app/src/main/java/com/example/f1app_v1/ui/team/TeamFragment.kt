package com.example.f1app_v1.ui.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.core.Result
import com.example.f1app_v1.data.model.TeamBaseInfo
import com.example.f1app_v1.data.remote.SeasonDataSource
import com.example.f1app_v1.data.remote.TeamDataSource
import com.example.f1app_v1.databinding.DriverItemBinding
import com.example.f1app_v1.databinding.FragmentTeamBinding
import com.example.f1app_v1.presentation.TeamViewModel
import com.example.f1app_v1.presentation.TeamViewModelFactory
import com.example.f1app_v1.repository.Season.SeasonRepositoryImpl
import com.example.f1app_v1.repository.Team.TeamRepositoryImpl
import com.example.f1app_v1.ui.adapters.Adapter
import com.example.f1app_v1.ui.adapters.RecyclerBindingInterface

class TeamFragment : Fragment(R.layout.fragment_team),
    Adapter.OnClickListener<TeamBaseInfo.Stage.Team> {

    private val viewModel by viewModels<TeamViewModel> {
        TeamViewModelFactory(
            SeasonRepositoryImpl(SeasonDataSource(RetrofitClient.webservice)),
            TeamRepositoryImpl(TeamDataSource(RetrofitClient.webservice))
        )
    }

    private lateinit var binding: FragmentTeamBinding
    private lateinit var adapter: Adapter<TeamBaseInfo.Stage.Team>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamBinding.bind(view)

        val bindingInterface = object :
            RecyclerBindingInterface<TeamBaseInfo.Stage.Team> {
            override fun bindData(item: TeamBaseInfo.Stage.Team, view: View) {
                val itemBinding = DriverItemBinding.bind(view)
                itemBinding.txtPosition.text =
                    "${item.result.position} - ${item.result.points} ${if (item.result.points > 1) "points" else "point"}"
                itemBinding.txtName.text = item.name
                itemBinding.txtNationality.text = item.nationality
            }
        }

        viewModel.fetchTeamsBaseInfo().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter = Adapter(
                        result.data.stage.teams,
                        R.layout.team_item,
                        bindingInterface, this@TeamFragment
                    )
                    binding.rvTeams.adapter = adapter
                }
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this.context,
                        "The information can´t be loaded",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        })
    }


    override fun onClick(team: TeamBaseInfo.Stage.Team) {
        var teamId = team.id
        teamId = teamId.replace(":", "%3a")
        viewModel.fetchTeam(teamId).observe(viewLifecycleOwner, Observer { team ->

            team.info?.let {
                val action = TeamFragmentDirections.actionTeamFragmentToTeamDetailFragment(
                    team.team.name,
                    it.country_of_residence ?: "-",
                    it.debut ?: "-",
                    it.foundation_year ?: "-",
                    team.competitors[0].name,
                    team.competitors[0].nationality,
                    team.competitors[1].name,
                    team.competitors[1].nationality
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