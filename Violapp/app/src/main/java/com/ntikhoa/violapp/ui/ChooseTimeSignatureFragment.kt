package com.ntikhoa.violapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.adapter.TimeSignatureAdapter
import com.ntikhoa.violapp.databinding.FragmentChooseTimeSignatureBinding

class ChooseTimeSignatureFragment : Fragment(R.layout.fragment_choose_time_signature),
    TimeSignatureAdapter.OnItemClickListener {

    private var _binding: FragmentChooseTimeSignatureBinding? = null
    private val binding get() = _binding!!

    var onItemClickListener: OnItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChooseTimeSignatureBinding.bind(view)

        val adapter = TimeSignatureAdapter(
            requireContext(),
            2,
            resources.getIntArray(R.array.time_signature)
        )
        adapter.onItemClickListener = this
        binding.recyclerView.adapter = adapter
    }

    override fun onClick(timeSignature: Int) {
        when (timeSignature) {
            2, 3, 4, 6, 9 -> {
                binding.textViewWarning.visibility = GONE
                if (onItemClickListener != null) {
                    onItemClickListener?.onClick(timeSignature)
                }
            }
            else -> {
                binding.textViewWarning.visibility = VISIBLE
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(timeSignature: Int)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}