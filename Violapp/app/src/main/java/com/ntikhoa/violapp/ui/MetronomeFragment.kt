package com.ntikhoa.violapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.FragmentMetronomeBinding
import com.ntikhoa.violapp.factory.TickFragmentFactory
import com.ntikhoa.violapp.model.TempoTerm


class MetronomeFragment : Fragment(R.layout.fragment_metronome),
    ChooseTempoTermFragment.OnItemClickListener,
    ChooseTimeSignatureFragment.OnItemClickListener {

    companion object {
        private const val MAX_TEMPO = 300
        private const val MIN_TEMPO = 20
    }

    private var _binding: FragmentMetronomeBinding? = null
    private val binding get() = _binding!!
    private val headerBinding get() = binding.header
    private val controllerBinding get() = binding.controller

    private val tempo get() = Integer.parseInt(binding.controller.textViewTempo.text.toString())

    private val tempoTerms get() = TempoTerm.TEMPO_TERMS

    private var tickFragment: TickFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMetronomeBinding.bind(view)

        addTickFragment()
        setOnBtnMuteListener()
        setOnClickIncrDecrBtn()
        setOnClickTempoTerm()
        setOnClickTimeSignature()
        setOnClickPlayButton()
    }

    private fun addTickFragment() {
        tickFragment = TickFragment(R.layout.fragment_2_tick)
        if (tickFragment != null) {
            childFragmentManager.commit {
                replace(R.id.fragment_container_tick, tickFragment!!)
            }
        }
    }

    private fun setOnBtnMuteListener() {
        headerBinding.btnMute.setOnCheckedChangeListener { buttonView, isChecked ->
            if (tickFragment != null) {
                tickFragment?.isMuted?.postValue(isChecked)
            }
        }
    }

    private fun setOnClickIncrDecrBtn() {
        controllerBinding.apply {
            btnIncr.setOnClickListener(onBtnIncrDecrClickListener)
            btnDecr.setOnClickListener(onBtnIncrDecrClickListener)
        }
    }

    private val onBtnIncrDecrClickListener = View.OnClickListener {
        controllerBinding.apply {
            when (it.id) {
                btnIncr.id -> {
                    if (tempo < MAX_TEMPO)
                        textViewTempo.text = (tempo + 1).toString()
                }
                btnDecr.id -> {
                    if (tempo > MIN_TEMPO)
                        textViewTempo.text = (tempo - 1).toString()
                }
            }
            setTempoTerm()
        }
    }

    private fun setTempoTerm() {
        for (index in tempoTerms.indices) {
            if (tempo >= tempoTerms[index].minTempo
                && tempo >= tempoTerms[index].maxTempo
            ) {
                controllerBinding.textViewTempoTerm.text = tempoTerms[index].term
            }
        }
    }

    private fun setOnClickTempoTerm() {
        controllerBinding.textViewTempoTerm.setOnClickListener {
            val chooseTempoTermFragment = ChooseTempoTermFragment.newInstance(tempo)
            childFragmentManager.commit {
                //set animation before replace or add fragment
                setCustomAnimations(0, 0, 0, R.anim.scrold_up)
                replace(R.id.fragment_container_tempo_term, chooseTempoTermFragment)
                addToBackStack(null)
            }
            chooseTempoTermFragment.onItemClickListener = this
        }
    }

    //on tempo term click listener
    override fun onClick(tempoTerm: TempoTerm) {
        controllerBinding.apply {
            textViewTempoTerm.text = tempoTerm.term
            textViewTempo.text = tempoTerm.getAVGtempo().toString()
            childFragmentManager.popBackStack()
        }
    }

    private fun setOnClickTimeSignature() {
        controllerBinding.textViewTimeSignature.setOnClickListener {
            val fragment = ChooseTimeSignatureFragment()
            childFragmentManager.commit {
                setCustomAnimations(R.anim.slide_in_up, 0, 0, R.anim.slide_out_down)
                replace(R.id.controller, fragment)
                addToBackStack(null)
            }
            fragment.onItemClickListener = this
        }
    }

    //on time signature click listener
    override fun onClick(timeSignature: Int) {
        tickFragment = TickFragmentFactory().createTickFragment(timeSignature)
        if (tickFragment != null) {
            childFragmentManager.commit {
                setCustomAnimations(R.anim.slide_in_to_left, R.anim.slide_out_to_left, 0, 0)
                replace(R.id.fragment_container_tick, tickFragment!!)
            }
        }
    }

    private fun setOnClickPlayButton() {
        controllerBinding.btnPlay.setOnCheckedChangeListener { buttonView, isChecked ->
            lockAndUnlockButton(!isChecked)
            if (tickFragment != null) {
                tickFragment?.isPlayed?.postValue(isChecked)
                tickFragment?.tempo = tempo
            }
        }
    }

    private fun lockAndUnlockButton(clickable: Boolean) {
        controllerBinding.apply {
            btnIncr.isClickable = clickable
            btnDecr.isClickable = clickable
            textViewTempoTerm.isClickable = clickable
            textViewTimeSignature.isClickable = clickable
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}