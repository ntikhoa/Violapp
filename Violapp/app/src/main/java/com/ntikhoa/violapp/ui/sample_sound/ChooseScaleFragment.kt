package com.ntikhoa.violapp.ui.sample_sound

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.adapter.ScaleAdapter
import com.ntikhoa.violapp.databinding.FragmentChooseScaleBinding
import com.ntikhoa.violapp.model.Scale.Scale


class ChooseScaleFragment : Fragment(R.layout.fragment_choose_scale),
    ScaleAdapter.OnItemClickListener {

    private var _binding: FragmentChooseScaleBinding? = null
    private val binding get() = _binding!!

    var onItemClickListener: OnItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChooseScaleBinding.bind(view)

        val adapter = ScaleAdapter()
        binding.recyclerViewScale.adapter = adapter
        adapter.onItemClickListener = this
    }

    override fun onClick(scale: Scale) {
        onItemClickListener?.let {
            it.onClick(scale)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnItemClickListener {
        fun onClick(scale: Scale)
    }
}