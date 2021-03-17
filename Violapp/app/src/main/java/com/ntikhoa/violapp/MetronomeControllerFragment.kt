package com.ntikhoa.violapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.ntikhoa.violapp.databinding.FragmentMetronomeControllerBinding


class MetronomeControllerFragment : Fragment(R.layout.fragment_metronome_controller),
    ChooseTempoTermFragment.OnItemClickListener {

    companion object {
        private const val MAX_TEMPO = 300
        private const val MIN_TEMPO = 20
    }

    private var _binding: FragmentMetronomeControllerBinding? = null
    private val binding get() = _binding!!

    private val tempo get() = Integer.parseInt(binding.viewTempo.textViewTempo.text.toString())

    private val tempoTerms get() = TempoTerm.TEMPO_TERMS

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMetronomeControllerBinding.bind(view)

        setOnClickIncrDecrBtn()
        setOnClickTempoTerm()
    }

    private fun setOnClickIncrDecrBtn() {
        binding.apply {
            btnIncr.setOnClickListener(onBtnIncrDecrClickListener)
            btnDecr.setOnClickListener(onBtnIncrDecrClickListener)
        }
    }

    private val onBtnIncrDecrClickListener = View.OnClickListener {
        binding.apply {
            when (it.id) {
                btnIncr.id -> {
                    if (tempo < MAX_TEMPO)
                        viewTempo.textViewTempo.text = (tempo + 1).toString()
                }
                btnDecr.id -> {
                    if (tempo > MIN_TEMPO)
                        viewTempo.textViewTempo.text = (tempo - 1).toString()
                }
            }

            setTempoTerm()
        }
    }

    private fun setOnClickTempoTerm() {
        binding.textViewTempoTerm.setOnClickListener {

            val chooseTempoTermFragment = ChooseTempoTermFragment()
            parentFragmentManager.commit {
                //set animation before replace or add fragment
                setCustomAnimations(0, 0, 0, R.anim.scrold_up)
                replace(R.id.fragment_tempo_term, chooseTempoTermFragment)
                addToBackStack(null)
            }

            chooseTempoTermFragment.onItemClickListener = this
        }
    }

    private fun setTempoTerm() {
        for (index in tempoTerms.indices) {
            if (tempo >= tempoTerms[index].minTempo
                && tempo >= tempoTerms[index].maxTempo
            ) {
                binding.textViewTempoTerm.text = tempoTerms[index].term
            }
        }
    }

    override fun onClick(tempoTerm: TempoTerm) {
        binding.apply {
            textViewTempoTerm.text = tempoTerm.term
            viewTempo.textViewTempo.text = tempoTerm.getAVGtempo().toString()
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}