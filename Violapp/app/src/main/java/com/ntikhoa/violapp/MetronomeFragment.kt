package com.ntikhoa.violapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ntikhoa.violapp.databinding.FragmentMetronomeBinding


class MetronomeFragment : Fragment(R.layout.fragment_metronome),
    MetronomeControllerFragment.OnTimeSignatureClick {

    private var _binding: FragmentMetronomeBinding? = null
    private val binding get() = _binding!!


    private lateinit var controller: MetronomeControllerFragment
    private lateinit var tickFragment: TickFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMetronomeBinding.bind(view)

        addControllerFragment()
        addTickFragment()
    }

    private fun addControllerFragment() {
        val ft = childFragmentManager.beginTransaction()
        controller = MetronomeControllerFragment()
        controller.onTimeSignatureClick = this
        ft.replace(R.id.fragment_controller, controller)
        ft.commit()
    }

    private fun addTickFragment() {
        val ft = childFragmentManager.beginTransaction()
        tickFragment = TickFragment(R.layout.fragment_2_tick)
        ft.replace(R.id.fragment_container_tick, tickFragment)
        ft.commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(layoutResId: Int) {
        val ft = childFragmentManager.beginTransaction()
        tickFragment = TickFragment(layoutResId)
        ft.replace(R.id.fragment_container_tick, tickFragment)
        ft.commit()
    }
}