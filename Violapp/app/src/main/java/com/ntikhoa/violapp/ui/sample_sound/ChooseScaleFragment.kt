package com.ntikhoa.violapp.ui.sample_sound

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.adapter.ScaleAdapter
import com.ntikhoa.violapp.databinding.FragmentChooseScaleBinding
import com.ntikhoa.violapp.model.Scale.AllNoteScale
import com.ntikhoa.violapp.model.Scale.Scale
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class ChooseScaleFragment : Fragment(R.layout.fragment_choose_scale),
    ScaleAdapter.OnItemClickListener {

    private var _binding: FragmentChooseScaleBinding? = null
    private val binding get() = _binding!!

    private var currentScale: Scale = AllNoteScale()

    var onItemClickListener: OnItemClickListener? = null

    private lateinit var adapter: ScaleAdapter

    companion object {
        private const val CURRENT_SCALE = "current scale"

        @JvmStatic
        fun newInstance(scale: Scale) =
            ChooseScaleFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CURRENT_SCALE, scale)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentScale = it.getParcelable<Scale>(CURRENT_SCALE)!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChooseScaleBinding.bind(view)

        adapter = ScaleAdapter(currentScale)
        binding.recyclerViewScale.adapter = adapter
        adapter.onItemClickListener = this
    }

    override fun onClick(scale: Scale, index: Int) {
        onItemClickListener?.let {
            it.onClick(scale, index)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnItemClickListener {
        fun onClick(scale: Scale, index: Int)
    }
}