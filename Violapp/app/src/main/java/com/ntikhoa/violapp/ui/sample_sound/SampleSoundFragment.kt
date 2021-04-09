package com.ntikhoa.violapp.ui.sample_sound

import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.FragmentSampleSoundBinding
import com.ntikhoa.violapp.databinding.IncludeStringBinding
import com.ntikhoa.violapp.model.CmajorScale


class SampleSoundFragment : Fragment(R.layout.fragment_sample_sound),
    View.OnTouchListener {

    private var _binding: FragmentSampleSoundBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapButtonNote: MapButtonNote
    private val map get() = mapButtonNote.maps

    private val scale = CmajorScale()

    private var noteBtnList = ArrayList<ArrayList<ImageButton>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSampleSoundBinding.bind(view)

        mapButtonNote = MapButtonNote(
            binding.layoutGString,
            binding.layoutDString,
            binding.layoutAString,
            binding.layoutEString
        )

        preprocessData()
        setStringOnClick()
        setOnScaleClick()
    }

    private fun preprocessData() {
        binding.apply {
            addBtnNoteToList(layoutGString)
            addBtnNoteToList(layoutDString)
            addBtnNoteToList(layoutAString)
            addBtnNoteToList(layoutEString)
        }
    }

    private fun addBtnNoteToList(stringBinding: IncludeStringBinding) {
        val btnNoteList = arrayListOf<ImageButton>()
        stringBinding.apply {
            btnNoteList.add(btn0)
            btnNoteList.add(btn1)
            btnNoteList.add(btn2)
            btnNoteList.add(btn3)
            btnNoteList.add(btn4)
            btnNoteList.add(btn5)
            btnNoteList.add(btn6)
            btnNoteList.add(btn7)
        }
        noteBtnList.add(btnNoteList)
    }

    private fun setStringOnClick() {
        for (i in noteBtnList.indices) {
            val str = noteBtnList[i]
            str.let {
                for (j in str.indices) {
                    str[j].setOnTouchListener(this@SampleSoundFragment)
                }
            }
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

    private fun setOnScaleClick() {
        binding.textViewScale.setOnClickListener {
            for (i in noteBtnList.indices) {
                val strList = scale.getStrListByIndex(i)
                for (j in noteBtnList[i].indices) {
                    if (!strList?.contains(noteBtnList[i][j].id)!!)
                        noteBtnList[i][j].visibility = INVISIBLE
                    else noteBtnList[i][j].visibility = VISIBLE
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}