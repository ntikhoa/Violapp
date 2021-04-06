package com.ntikhoa.violapp.ui.sample_sound

import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.FragmentSampleSoundBinding
import com.ntikhoa.violapp.databinding.IncludeStringBinding


class SampleSoundFragment : Fragment(R.layout.fragment_sample_sound),
    View.OnTouchListener {

    private var _binding: FragmentSampleSoundBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapButtonNote: MapButtonNote
    private val map get() = mapButtonNote.maps

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSampleSoundBinding.bind(view)

        mapButtonNote = MapButtonNote(
            binding.layoutGString,
            binding.layoutDString,
            binding.layoutAString,
            binding.layoutEString
        )
        setStringOnClick()
    }

    private fun setStringOnClick() {
        setBtnNoteOnClick(binding.layoutGString)
        setBtnNoteOnClick(binding.layoutDString)
        setBtnNoteOnClick(binding.layoutAString)
        setBtnNoteOnClick(binding.layoutEString)
    }

    private fun setBtnNoteOnClick(stringBinding: IncludeStringBinding) {
        stringBinding.apply {
            btn0.setOnTouchListener(this@SampleSoundFragment)
            btn1.setOnTouchListener(this@SampleSoundFragment)
            btn2.setOnTouchListener(this@SampleSoundFragment)
            btn3.setOnTouchListener(this@SampleSoundFragment)
            btn4.setOnTouchListener(this@SampleSoundFragment)
            btn5.setOnTouchListener(this@SampleSoundFragment)
            btn6.setOnTouchListener(this@SampleSoundFragment)
            btn7.setOnTouchListener(this@SampleSoundFragment)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val note = map[v as ImageButton]
        note?.let {
            val mediaPlayer = note.getMediaPlayer(requireContext())
            when (event?.actionMasked) {
                ACTION_DOWN -> {
                    binding.apply {
                        imageNote.setImageResource(note.imagesResId)
                        textViewNote.text = note.name
                    }
                    mediaPlayer.start()
                }
                ACTION_UP -> mediaPlayer.stop()
            }
        }
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}