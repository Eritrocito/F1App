package com.example.f1app_v1.ui.RaceDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.f1app_v1.R
import com.example.f1app_v1.databinding.FragmentDriverDetailBinding
import com.example.f1app_v1.databinding.FragmentRaceDetailBinding
import com.example.f1app_v1.ui.DriverDetail.DriverDetailFragmentArgs


class RaceDetailFragment : Fragment(R.layout.fragment_race_detail) {

    private lateinit var binding: FragmentRaceDetailBinding
    private val args by navArgs<RaceDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentRaceDetailBinding.bind(view)
        binding.txtGpName.text=args.gpName
        binding.txtCircuit.text="${args.name}"
        binding.txtCity.text="City: ${args.city}"
        binding.txtCountry.text="Country: ${args.country}"
        binding.txtDebut.text="Debut: ${args.debut}"
        binding.txtLength.text="Length: ${args.length}m"
        binding.txtLaps.text="Laps: ${args.laps}"



    }
}