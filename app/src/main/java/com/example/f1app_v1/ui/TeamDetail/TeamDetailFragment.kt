package com.example.f1app_v1.ui.TeamDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.f1app_v1.R
import com.example.f1app_v1.databinding.FragmentTeamDetailBinding


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
        val comp1=args.comp1Name.let {
                val splitted = it.split(",")
                "${splitted[1]} ${splitted[0]} "
            }
        val comp2=args.comp2Name.let {
            val splitted = it.split(",")
            "${splitted[1]} ${splitted[0]} "
        }
        binding.txtComp1.text="1 - ${comp1}, ${args.comp1Nat}"
        binding.txtComp2.text="2- ${comp2}, ${args.comp2Nat}"
    }
}

