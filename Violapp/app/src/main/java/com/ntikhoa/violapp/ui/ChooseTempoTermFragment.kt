package com.ntikhoa.violapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.adapter.TempoTermAdapter
import com.ntikhoa.violapp.databinding.FragmentChooseTempoTermBinding
import com.ntikhoa.violapp.model.TempoTerm


class ChooseTempoTermFragment : Fragment(R.layout.fragment_choose_tempo_term),
    TempoTermAdapter.OnItemClickListener {

    private var currentTempo = DEFAULT_TEMPO

    companion object {
        private const val DEFAULT_TEMPO = 60
        const val CURRENT_TEMPO = "current tempo"

        @JvmStatic
        fun newInstance(currentTempo: Int) =
            ChooseTempoTermFragment().apply {
                arguments = Bundle().apply {
                    putInt(CURRENT_TEMPO, currentTempo)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            currentTempo = it.getInt(CURRENT_TEMPO, DEFAULT_TEMPO)
        }
    }

    private var _binding: FragmentChooseTempoTermBinding? = null
    private val binding get() = _binding!!

    var onItemClickListener: OnItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChooseTempoTermBinding.bind(view)

        val adapter = TempoTermAdapter(requireContext(), currentTempo)
        binding.recyclerView.adapter = adapter
        adapter.onItemClickListener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnItemClickListener {
        fun onClick(tempoTerm: TempoTerm)
    }

    override fun onClick(tempoTerm: TempoTerm) {
        if (onItemClickListener != null) {
            onItemClickListener?.onClick(tempoTerm)
        }
    }
}