package com.ntikhoa.violapp.ui.sample_sound

import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.FragmentSampleSoundBinding
import com.ntikhoa.violapp.databinding.IncludeStringBinding
import com.ntikhoa.violapp.model.AmajorScale


class SampleSoundFragment : Fragment(R.layout.fragment_sample_sound),
    View.OnTouchListener {

    private var _binding: FragmentSampleSoundBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapButtonNote: MapButtonNote
    private val map get() = mapButtonNote.maps

    private val scale = AmajorScale()

    private lateinit var Gstr: List<ImageButton>
    private lateinit var Dstr: List<ImageButton>
    private lateinit var Astr: List<ImageButton>
    private lateinit var Estr: List<ImageButton>

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
        when (stringBinding.root.id) {
            R.id.layout_G_string -> Gstr = btnNoteList
            R.id.layout_D_string -> Dstr = btnNoteList
            R.id.layout_A_string -> Astr = btnNoteList
            R.id.layout_E_string -> Estr = btnNoteList
        }
    }

    private fun getStrBinding(strBindingId: Int): List<ImageButton>? {
        when (strBindingId) {
            R.id.layout_G_string -> return Gstr
            R.id.layout_D_string -> return Dstr
            R.id.layout_A_string -> return Astr
            R.id.layout_E_string -> return Estr
            else -> return null
        }
    }

    private fun setStringOnClick() {
        setBtnNoteOnClick(binding.layoutGString)
        setBtnNoteOnClick(binding.layoutDString)
        setBtnNoteOnClick(binding.layoutAString)
        setBtnNoteOnClick(binding.layoutEString)
    }

    private fun setBtnNoteOnClick(stringBinding: IncludeStringBinding) {
        val cc = getStrBinding(stringBinding.root.id)
        cc?.let {
            for (i in cc.indices) {
                cc[i].setOnTouchListener(this@SampleSoundFragment)
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
            val strList = scale.getGstrList()
            for (i in Gstr.indices) {
                if (!strList.contains(Gstr[i].id))
                    Gstr[i].visibility = INVISIBLE
                else Gstr[i].visibility = VISIBLE
            }

            val strList1 = scale.getDstrList()
            for (i in Dstr.indices) {
                if (!strList1.contains(Dstr[i].id))
                    Dstr[i].visibility = INVISIBLE
                else Dstr[i].visibility = VISIBLE
            }

            val strList2 = scale.getAstrList()
            for (i in Astr.indices) {
                if (!strList2.contains(Astr[i].id))
                    Astr[i].visibility = INVISIBLE
                else Astr[i].visibility = VISIBLE
            }

            val strList3 = scale.getEstrList()
            for (i in Estr.indices) {
                if (!strList3.contains(Estr[i].id))
                    Estr[i].visibility = INVISIBLE
                else Estr[i].visibility = VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}