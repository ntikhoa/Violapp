package com.ntikhoa.violapp

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.View
import com.ntikhoa.violapp.databinding.FragmentChooseTempoTermBinding


class ChooseTempoTermFragment : Fragment(R.layout.fragment_choose_tempo_term),
    TempoTermAdapter.OnItemClickListener {

    private var _binding: FragmentChooseTempoTermBinding? = null
    private val binding get() = _binding!!

    var onItemClickListener: OnItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChooseTempoTermBinding.bind(view)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.scrold_up)

        val adapter = TempoTermAdapter()
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