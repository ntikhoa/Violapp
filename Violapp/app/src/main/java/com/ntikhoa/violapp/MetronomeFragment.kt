package com.ntikhoa.violapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ntikhoa.violapp.databinding.FragmentMetronomeBinding


class MetronomeFragment : Fragment(R.layout.fragment_metronome) {

    private var _binding: FragmentMetronomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var controller: MetronomeControllerFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMetronomeBinding.bind(view)

        addControllerFragment()

    }

    private fun addControllerFragment() {
        val ft = childFragmentManager.beginTransaction()
        controller = MetronomeControllerFragment()
        ft.replace(R.id.fragment_controller, controller)
        ft.commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}