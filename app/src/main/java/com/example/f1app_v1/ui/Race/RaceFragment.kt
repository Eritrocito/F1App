package com.example.f1app_v1.ui.Race

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.data.model.RaceBaseInfo
import com.example.f1app_v1.data.remote.RaceDataSource
import com.example.f1app_v1.data.remote.SeasonDataSource
import com.example.f1app_v1.databinding.FragmentRaceBinding
import com.example.f1app_v1.presentation.RaceViewModel
import com.example.f1app_v1.presentation.RaceViewModelFactory
import com.example.f1app_v1.repository.Race.RaceRepositoryImpl
import com.example.f1app_v1.repository.Season.SeasonRepositoryImpl
import com.example.f1app_v1.core.Result
import com.example.f1app_v1.databinding.RaceItemBinding
import com.example.f1app_v1.ui.adapters.Adapter
import com.example.f1app_v1.ui.adapters.RecyclerBindingInterface


class RaceFragment : Fragment(R.layout.fragment_race),
    Adapter.OnClickListener<RaceBaseInfo.Stage.Race> {

    private val viewModel by viewModels<RaceViewModel> {
        RaceViewModelFactory(
            SeasonRepositoryImpl(SeasonDataSource(RetrofitClient.webservice)),
            RaceRepositoryImpl(RaceDataSource(RetrofitClient.webservice))
        )
    }

    private lateinit var binding: FragmentRaceBinding
    private lateinit var adapter: Adapter<RaceBaseInfo.Stage.Race>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRaceBinding.bind(view)

        val bindingInterface = object :
            RecyclerBindingInterface<RaceBaseInfo.Stage.Race> {
            override fun bindData(item: RaceBaseInfo.Stage.Race, view: View) {
                val itemBinding = RaceItemBinding.bind(view)
                itemBinding.txtGPname.text = item.description
                itemBinding.txtStatus.text = item.status
                itemBinding.txtStart.text = "Start ${item.scheduled.split("T")[0]}"
                itemBinding.txtEnd.text = "End ${item.scheduled_end.split("T")[0]}"
            }
        }

        viewModel.fetchRaceBaseInfo().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter = Adapter(
                        result.data.stage.stages,
                        R.layout.race_item,
                        bindingInterface, this@RaceFragment
                    )
                    binding.rvRaces.adapter = adapter
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

    override fun onClick(race: RaceBaseInfo.Stage.Race) {
        val item = race.venue
        val action = RaceFragmentDirections.actionRaceFragmentToRaceDetailFragment(
            item.name,
            item.city,
            item.country,
            item.length?.toString() ?: "-",
            item.debut?.toString() ?: "-",
            item.laps?.toString() ?: "-",
            race.description
        )
        findNavController().navigate(action)
    }
}