package com.ntikhoa.violapp.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var navController: NavController

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.btnMetronome.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToMetronomeFragment()
            navController.navigate(action)
        }

        binding.btnSampleSound.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToSampleSoundFragment()
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}