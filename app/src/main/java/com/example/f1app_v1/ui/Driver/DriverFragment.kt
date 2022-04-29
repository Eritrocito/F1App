package com.example.f1app_v1.ui.Driver

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.data.model.DriverBaseInfo
import com.example.f1app_v1.data.remote.DriverDataSource
import com.example.f1app_v1.data.remote.SeasonDataSource
import com.example.f1app_v1.databinding.FragmentDriverBinding
import com.example.f1app_v1.presentation.DriverViewModel
import com.example.f1app_v1.presentation.DriverViewModelFactory
import com.example.f1app_v1.repository.Driver.DriverRepositoryImpl
import com.example.f1app_v1.repository.Season.SeasonRepositoryImpl
import com.example.f1app_v1.ui.adapters.DriverAdapter


class DriverFragment : Fragment(R.layout.fragment_driver), DriverAdapter.OnDriverClickListener {

    private val viewModel by viewModels<DriverViewModel> {
        DriverViewModelFactory(
            SeasonRepositoryImpl(SeasonDataSource(RetrofitClient.webservice)),
            DriverRepositoryImpl(DriverDataSource(RetrofitClient.webservice))
        )
    }

    private lateinit var binding: FragmentDriverBinding
    private lateinit var adapter: DriverAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDriverBinding.bind(view)

        viewModel.fetchDriversBaseInfo().observe(viewLifecycleOwner, Observer { result ->
            adapter = DriverAdapter(result, this@DriverFragment)
            binding.rvDrivers.adapter = adapter

        })
    }

    override fun onDriverClick(driver: DriverBaseInfo.Stage.Comp) {
        //Log.d("results", "${driver.result.position}")
        var driverId = driver.id
        driverId = driverId.replace(":", "%3a")
        viewModel.fetchDriver(driverId).observe(viewLifecycleOwner, Observer { driver ->

            driver.info?.let {
                val action = DriverFragmentDirections.actionDriverFragmentToDriverDetailFragment(
                    //it.salary?.toString() ?: "-",
                    it.salary?.let {"USD ${it.toFloat().div(1000000)}M" } ?: "-",
                    it.dateofbirth ?: "-",
                    it.placeofbirth ?: "-",
                    it.country_of_residence ?: "-",
                    it.debut ?: "-",
                    it.first_victory ?: "-",
                    it.wcs_won ?: 0,
                    driver.teams[0].name ?: "-",
                    driver.teams[0].nationality ?: "-"
                )
                findNavController().navigate(action)
            } ?: Toast.makeText(
                this.context,
                "Driver information not available right now",
                Toast.LENGTH_SHORT
            ).show()


        })
    }
}