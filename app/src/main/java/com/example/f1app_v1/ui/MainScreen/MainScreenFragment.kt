package com.example.f1app_v1.ui.MainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.f1app_v1.R
import com.example.f1app_v1.databinding.FragmentMainScreenBinding


class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

        private lateinit var itemBinding:FragmentMainScreenBinding

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)

                itemBinding= FragmentMainScreenBinding.bind(view)
                itemBinding.btnRaces.setOnClickListener {
                        val action=MainScreenFragmentDirections.actionMainScreenFragmentToRaceFragment()
                        findNavController().navigate(action)
                }
                itemBinding.btnDrivers.setOnClickListener {
                        val action=MainScreenFragmentDirections.actionMainScreenFragmentToDriverFragment()
                        findNavController().navigate(action)
                }
        }
}