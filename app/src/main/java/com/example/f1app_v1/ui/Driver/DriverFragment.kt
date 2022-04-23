package com.example.f1app_v1.ui.Driver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.data.remote.DataSource
import com.example.f1app_v1.databinding.DriverItemBinding
import com.example.f1app_v1.databinding.FragmentDriverBinding
import com.example.f1app_v1.presentation.DriverViewModel
import com.example.f1app_v1.presentation.DriverViewModelFactory
import com.example.f1app_v1.repository.DriverRepositoryImpl
import com.example.f1app_v1.ui.adapters.DriverAdapter


class DriverFragment : Fragment(R.layout.fragment_driver) {

    private val viewModel by viewModels<DriverViewModel> {
        DriverViewModelFactory(DriverRepositoryImpl(DataSource(RetrofitClient.webservice)))
    }

    private lateinit var binding: FragmentDriverBinding
    private lateinit var adapter: DriverAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDriverBinding.bind(view)


        viewModel.fetchDrivers().observe(viewLifecycleOwner, Observer { result ->
            /*for (item in result.stage.competitors)
                Log.d(
                    "Competidores",
                    "${item.id}"
                )
            for(item in result){
                if(item.info.country_of_residence.isNotEmpty()){
                    //Log.d("Drivers","${item.info.country_of_residence}")
                }*/
            adapter = DriverAdapter(result)
            binding.rvDrivers.adapter = adapter

        })

    }
}