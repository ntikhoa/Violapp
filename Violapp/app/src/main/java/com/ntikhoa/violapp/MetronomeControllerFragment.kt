package com.ntikhoa.violapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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

    private val TEMPO_TERMS get() = TempoTerm.TEMPO_TERMS

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMetronomeControllerBinding.bind(view)

        setOnClickIncrDecrBtn()
        setOnClickTempoTerm()
    }

    fun setOnClickIncrDecrBtn() {
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
                        viewTempo.textViewTempo.setText((tempo + 1).toString())
                }
                btnDecr.id -> {
                    if (tempo > MIN_TEMPO)
                        viewTempo.textViewTempo.setText((tempo - 1).toString())
                }
            }

            setTempoTerm()
        }
    }

    fun setOnClickTempoTerm() {
        binding.textViewTempoTerm.setOnClickListener {
            val ft = parentFragmentManager.beginTransaction()
            val chooseTempoTermFragment = ChooseTempoTermFragment()
            ft.add(R.id.fragment_tempo_term, chooseTempoTermFragment)
            ft.addToBackStack(null)
            ft.commit()

            chooseTempoTermFragment.onItemClickListener = this
        }
    }

    private fun setTempoTerm() {
        for (index in TEMPO_TERMS.indices) {
            if (tempo >= TEMPO_TERMS[index].minTempo
                && tempo >= TEMPO_TERMS[index].maxTempo
            ) {
                binding.textViewTempoTerm.setText(TEMPO_TERMS[index].term)
            }
        }
    }

    override fun onClick(tempoTerm: TempoTerm) {
        binding.apply {
            textViewTempoTerm.setText(tempoTerm.term)
            viewTempo.textViewTempo.setText(tempoTerm.getAVGtempo().toString())
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}