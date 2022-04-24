package com.example.f1app_v1.ui.race

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.data.remote.DataSource
import com.example.f1app_v1.data.remote.RaceDataSource
import com.example.f1app_v1.databinding.FragmentDriverBinding
import com.example.f1app_v1.databinding.FragmentRaceBinding
import com.example.f1app_v1.presentation.RaceViewModel
import com.example.f1app_v1.presentation.RaceViewModelFactory
import com.example.f1app_v1.repository.Driver.DriverRepositoryImpl
import com.example.f1app_v1.repository.Race.RaceRepositoryImpl
import com.example.f1app_v1.ui.adapters.DriverAdapter
import com.example.f1app_v1.ui.adapters.RaceAdapter


class RaceFragment : Fragment(R.layout.fragment_race) {

    private val viewModel by viewModels<RaceViewModel> {
        RaceViewModelFactory(RaceRepositoryImpl(RaceDataSource(RetrofitClient.webservice)))
    }

    private lateinit var binding: FragmentRaceBinding
    private lateinit var adapter: RaceAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRaceBinding.bind(view)

        viewModel.fetchRaceBaseInfo().observe(viewLifecycleOwner, Observer { result ->
            adapter = RaceAdapter(result)
            binding.rvRaces.adapter = adapter

        })
    }
}