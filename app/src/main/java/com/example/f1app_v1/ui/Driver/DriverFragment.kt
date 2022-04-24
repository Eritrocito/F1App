package com.example.f1app_v1.ui.Driver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.data.model.Driver
import com.example.f1app_v1.data.remote.DataSource
import com.example.f1app_v1.databinding.FragmentDriverBinding
import com.example.f1app_v1.presentation.DriverViewModel
import com.example.f1app_v1.presentation.DriverViewModelFactory
import com.example.f1app_v1.repository.Driver.DriverRepositoryImpl
import com.example.f1app_v1.ui.adapters.DriverAdapter


class DriverFragment : Fragment(R.layout.fragment_driver), DriverAdapter.OnDriverClickListener {

    private val viewModel by viewModels<DriverViewModel> {
        DriverViewModelFactory(DriverRepositoryImpl(DataSource(RetrofitClient.webservice)))
    }

    private lateinit var binding: FragmentDriverBinding
    private lateinit var adapter: DriverAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDriverBinding.bind(view)

        viewModel.fetchDrivers().observe(viewLifecycleOwner, Observer { result ->
            adapter = DriverAdapter(result, this@DriverFragment)
            binding.rvDrivers.adapter = adapter

        })


    }

    override fun onDriverClick(driver: Driver) {
        val item = driver.info
        val action = DriverFragmentDirections.actionDriverFragmentToDriverDetailFragment(item.salary,item.dateofbirth,item.placeofbirth,item.country_of_residence,
        item.debut,item.first_victory,item.wcs_won,driver.teams[0].name,driver.teams[0].nationality)
        findNavController().navigate(action)
    }


}