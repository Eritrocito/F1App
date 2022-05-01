package com.example.f1app_v1.ui.Driver

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.f1app_v1.R
import com.example.f1app_v1.RetrofitClient
import com.example.f1app_v1.core.Result
import com.example.f1app_v1.data.model.DriverBaseInfo
import com.example.f1app_v1.data.remote.DriverDataSource
import com.example.f1app_v1.data.remote.SeasonDataSource
import com.example.f1app_v1.databinding.DriverItemBinding
import com.example.f1app_v1.databinding.FragmentDriverBinding
import com.example.f1app_v1.presentation.DriverViewModel
import com.example.f1app_v1.presentation.DriverViewModelFactory
import com.example.f1app_v1.repository.Driver.DriverRepositoryImpl
import com.example.f1app_v1.repository.Season.SeasonRepositoryImpl
import com.example.f1app_v1.ui.adapters.Adapter
import com.example.f1app_v1.ui.adapters.DriverAdapter
import com.example.f1app_v1.ui.adapters.RecyclerBindingInterface
import kotlinx.coroutines.NonDisposableHandle.parent


class DriverFragment : Fragment(R.layout.fragment_driver),
    Adapter.OnClickListener<DriverBaseInfo.Stage.Comp> {

    private val viewModel by viewModels<DriverViewModel> {
        DriverViewModelFactory(
            SeasonRepositoryImpl(SeasonDataSource(RetrofitClient.webservice)),
            DriverRepositoryImpl(DriverDataSource(RetrofitClient.webservice))
        )
    }

    private lateinit var binding: FragmentDriverBinding
    private lateinit var adapter: Adapter<DriverBaseInfo.Stage.Comp>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDriverBinding.bind(view)


        val bindingInterface = object :
            RecyclerBindingInterface<DriverBaseInfo.Stage.Comp> {
            override fun bindData(item: DriverBaseInfo.Stage.Comp, view: View) {
                val itemBinding = DriverItemBinding.bind(view)
                itemBinding.txtName.text = item.name.let {
                    val splitted = it.split(",")
                    "${splitted[1]} ${splitted[0]} "
                }
                itemBinding.txtNationality.text = item.nationality
                itemBinding.txtPosition.text =
                    "${item.result.position} - ${item.result.points} ${if (item.result.points > 1) "points" else "point"}"
            }
        }


        viewModel.fetchDriversBaseInfo().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE

                    adapter = Adapter(
                        result.data.stage.competitors,
                        R.layout.driver_item,
                        bindingInterface, this@DriverFragment
                    )
                    binding.rvDrivers.adapter = adapter
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

    override fun onClick(driver: DriverBaseInfo.Stage.Comp) {
        var driverId = driver.id
        driverId = driverId.replace(":", "%3a")
        viewModel.fetchDriver(driverId).observe(viewLifecycleOwner, Observer { driver ->

            driver.info?.let {
                val action = DriverFragmentDirections.actionDriverFragmentToDriverDetailFragment(
                    //it.salary?.toString() ?: "-",
                    it.salary?.let { "USD ${it.toFloat().div(1000000)}M" } ?: "-",
                    it.dateofbirth ?: "-",
                    it.placeofbirth ?: "-",
                    it.country_of_residence ?: "-",
                    it.debut ?: "-",
                    it.first_victory ?: "-",
                    it.wcs_won ?: 0,
                    driver.teams[0].name ?: "-",
                    driver.teams[0].nationality ?: "-",
                    driver.competitor.name ?: "- "
                )
                findNavController().navigate(action)
            } ?: Toast.makeText(
                this.context,
                "Driver information not available right now",
                Toast.LENGTH_SHORT
            ).show()


        })
    }

    /*override fun onDriverClick(driver: DriverBaseInfo.Stage.Comp) {
        //Log.d("results", "${driver.result.position}")
        var driverId = driver.id
        driverId = driverId.replace(":", "%3a")
        viewModel.fetchDriver(driverId).observe(viewLifecycleOwner, Observer { driver ->

            driver.info?.let {
                val action = DriverFragmentDirections.actionDriverFragmentToDriverDetailFragment(
                    //it.salary?.toString() ?: "-",
                    it.salary?.let { "USD ${it.toFloat().div(1000000)}M" } ?: "-",
                    it.dateofbirth ?: "-",
                    it.placeofbirth ?: "-",
                    it.country_of_residence ?: "-",
                    it.debut ?: "-",
                    it.first_victory ?: "-",
                    it.wcs_won ?: 0,
                    driver.teams[0].name ?: "-",
                    driver.teams[0].nationality ?: "-",
                    driver.competitor.name ?: "- "
                )
                findNavController().navigate(action)
            } ?: Toast.makeText(
                this.context,
                "Driver information not available right now",
                Toast.LENGTH_SHORT
            ).show()


        })
    }*/
}