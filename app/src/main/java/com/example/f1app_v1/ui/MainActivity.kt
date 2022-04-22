package com.example.f1app_v1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.data.remote.DataSource
import com.example.f1app_v1.presentation.DriverViewModel
import com.example.f1app_v1.presentation.DriverViewModelFactory
import com.example.f1app_v1.repository.DriverRepositoryImpl

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<DriverViewModel> {
        DriverViewModelFactory(DriverRepositoryImpl(DataSource(RetrofitClient.webservice)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchDrivers().observe(this, Observer { result ->
            /*for (item in result.stage.competitors)
                Log.d(
                    "Competidores",
                    "${item.id}"
                )*/
            for(item in result){
                if(item.info.country_of_residence.isNotEmpty()){
                        Log.d("Drivers","${item.info.country_of_residence}")
                    }
                }

        })


    }
}