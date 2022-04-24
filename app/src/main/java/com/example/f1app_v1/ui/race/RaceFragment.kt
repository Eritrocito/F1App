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
import com.example.f1app_v1.presentation.RaceViewModel
import com.example.f1app_v1.presentation.RaceViewModelFactory
import com.example.f1app_v1.repository.Driver.DriverRepositoryImpl
import com.example.f1app_v1.repository.Race.RaceRepositoryImpl


class RaceFragment : Fragment(R.layout.fragment_race) {

    private val viewModel by viewModels<RaceViewModel> {
        RaceViewModelFactory(RaceRepositoryImpl(RaceDataSource(RetrofitClient.webservice)))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchRaceBaseInfo().observe(viewLifecycleOwner, Observer { result ->
            for (item in result.stage.stages){
                Log.d("RaceFragment", "${item.description}")
            }

        })
    }
}