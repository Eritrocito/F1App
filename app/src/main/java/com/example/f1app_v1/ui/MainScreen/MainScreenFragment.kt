package com.example.f1app_v1.ui.MainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.f1app_v1.R
import com.example.f1app_v1.core.Timer
import com.example.f1app_v1.databinding.FragmentMainScreenBinding


class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private lateinit var itemBinding: FragmentMainScreenBinding

    private var timer = Timer.timer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemBinding = FragmentMainScreenBinding.bind(view)


        itemBinding.btnRaces.setOnClickListener {
            if (System.currentTimeMillis() - timer > 2000) {
                val action = MainScreenFragmentDirections.actionMainScreenFragmentToRaceFragment()
                timer = System.currentTimeMillis()
                findNavController().navigate(action)
            } else {
                Toast.makeText(this.context, "Wait for next call", Toast.LENGTH_SHORT).show()
            }
        }

        itemBinding.btnDrivers.setOnClickListener {
            if (System.currentTimeMillis() - timer > 2000) {
                val action = MainScreenFragmentDirections.actionMainScreenFragmentToDriverFragment()
                timer = System.currentTimeMillis()
                findNavController().navigate(action)
            } else {
                Toast.makeText(this.context, "Wait for next call", Toast.LENGTH_SHORT).show()
            }
        }

        itemBinding.btnConstructors.setOnClickListener {
            if (System.currentTimeMillis() - timer > 2000) {
                val action = MainScreenFragmentDirections.actionMainScreenFragmentToTeamFragment()
                timer = System.currentTimeMillis()
                findNavController().navigate(action)
            } else {
                Toast.makeText(this.context, "Wait for next call", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
