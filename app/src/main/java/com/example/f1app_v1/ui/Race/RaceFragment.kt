package com.example.f1app_v1.ui.Race

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
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
import com.example.f1app_v1.ui.adapters.RaceAdapter


class RaceFragment : Fragment(R.layout.fragment_race),RaceAdapter.OnRaceClickListener {

    private val viewModel by viewModels<RaceViewModel> {
        RaceViewModelFactory(SeasonRepositoryImpl(SeasonDataSource(RetrofitClient.webservice)),RaceRepositoryImpl(RaceDataSource(RetrofitClient.webservice)))
    }

    private lateinit var binding: FragmentRaceBinding
    private lateinit var adapter: RaceAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRaceBinding.bind(view)

        viewModel.fetchRaceBaseInfo().observe(viewLifecycleOwner, Observer { result ->
            adapter = RaceAdapter(result,this@RaceFragment)
            binding.rvRaces.adapter = adapter

        })
    }

    override fun onRaceClick(race: RaceBaseInfo.Stage.Race) {
        val item= race.venue
        val action = RaceFragmentDirections.actionRaceFragmentToRaceDetailFragment(item.name,item.city,item.country,
            item.length.toString()?: "-" ,item.debut.toString()?: "-",item.laps.toString()?: "-",race.description)
        findNavController().navigate(action)
    }
}