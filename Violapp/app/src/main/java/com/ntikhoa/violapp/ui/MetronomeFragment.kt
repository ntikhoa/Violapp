package com.ntikhoa.violapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.FragmentMetronomeBinding
import com.ntikhoa.violapp.factory.TickFragmentFactory


class MetronomeFragment : Fragment(R.layout.fragment_metronome),
    MetronomeControllerFragment.OnTimeSignatureClick,
    MetronomeControllerFragment.OnPlayBtnClick {

    private var _binding: FragmentMetronomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var controller: MetronomeControllerFragment

    private var tickFragment: TickFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMetronomeBinding.bind(view)

        addControllerFragment()
        addTickFragment()
        setOnBtnMuteListener()
    }

    private fun addControllerFragment() {
        val ft = childFragmentManager.beginTransaction()
        controller = MetronomeControllerFragment()
        ft.replace(R.id.fragment_controller, controller)
        ft.commit()
        controller.onTimeSignatureClick = this
        controller.onPlayBtnClick = this
    }

    private fun addTickFragment() {
        val ft = childFragmentManager.beginTransaction()
        tickFragment = TickFragment(R.layout.fragment_9_tick)
        if (tickFragment != null)
            ft.replace(R.id.fragment_container_tick, tickFragment!!)
        ft.commit()
    }

    private fun setOnBtnMuteListener() {
        binding.btnMute.setOnCheckedChangeListener { buttonView, isChecked ->
            if (tickFragment != null) {
                tickFragment?.isMuted?.postValue(isChecked)
            }
        }
    }

    override fun onClick(timeSignature: Int) {
        tickFragment = TickFragmentFactory().createTickFragment(timeSignature)
        if (tickFragment != null) {
            val ft = childFragmentManager.beginTransaction()
            ft.setCustomAnimations(R.anim.slide_in_to_left, R.anim.slide_out_to_left, 0, 0)
            ft.replace(R.id.fragment_container_tick, tickFragment!!)
            ft.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(isChecked: Boolean, tempo: Int) {
        if (tickFragment != null) {
            tickFragment?.isPlayed?.postValue(isChecked)
            tickFragment?.tempo = tempo
        }
    }
}