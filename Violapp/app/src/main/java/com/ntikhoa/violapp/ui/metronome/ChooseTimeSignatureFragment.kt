package com.ntikhoa.violapp.ui.metronome

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

    private var timeSignature = DEFAULT_TIME_SIGNATURE

    private lateinit var adapter: TimeSignatureAdapter

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            timeSignature = it.getInt(TIME_SIGNATURE)
        }
    }

    companion object {
        private const val TIME_SIGNATURE = "time signature"
        private const val DEFAULT_TIME_SIGNATURE = 2

        @JvmStatic
        fun newInstance(timeSignature: Int) =
            ChooseTimeSignatureFragment().apply {
                arguments = Bundle().apply {
                    putInt(TIME_SIGNATURE, timeSignature)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChooseTimeSignatureBinding.bind(view)

        adapter = TimeSignatureAdapter(
            requireContext(),
            timeSignature,
            resources.getIntArray(R.array.time_signature)
        )
        adapter.onItemClickListener = this
        binding.recyclerView.adapter = adapter
    }

    override fun onClick(timeSignature: Int) {
        when (timeSignature) {
            2, 3, 4, 6, 9 -> {
                binding.textViewWarning.visibility = GONE
                onItemClickListener?.let {
                    adapter.currentTimeSignature = timeSignature
                    adapter.notifyDataSetChanged()
                    it.onClick(timeSignature)
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