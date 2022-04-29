package com.example.f1app_v1.ui.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.f1app_v1.R
import com.example.f1app_v1.databinding.FragmentDriverDetailBinding
import com.example.f1app_v1.databinding.FragmentTeamDetailBinding
import com.example.f1app_v1.ui.DriverDetail.DriverDetailFragmentArgs


class TeamDetailFragment : Fragment(R.layout.fragment_team_detail) {

    private lateinit var binding: FragmentTeamDetailBinding
    private val args by navArgs<TeamDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentTeamDetailBinding.bind(view)

        binding.txtTeamName.text=args.name
        binding.txtCountryofresidence.text="Current country: ${args.countryOfResidence}"
        binding.txtFoundation.text="Founded: ${args.yearOfFoundation}"
        binding.txtDebut.text="Debut in F1: ${args.debut}"
        binding.txtComp1.text="${args.comp1Name}, ${args.comp1Nat}"
        binding.txtComp2.text="${args.comp2Name}, ${args.comp2Nat}"
    }
}