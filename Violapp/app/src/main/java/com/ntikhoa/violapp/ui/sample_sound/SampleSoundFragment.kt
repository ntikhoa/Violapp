package com.ntikhoa.violapp.ui.sample_sound

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.FragmentSampleSoundBinding
import com.ntikhoa.violapp.databinding.IncludeStringBinding
import com.ntikhoa.violapp.model.Scale.AllNoteScale
import com.ntikhoa.violapp.model.Scale.Scale
import com.ntikhoa.violapp.model.note.MapButtonNote
import com.ntikhoa.violapp.util.ViewUtil
import com.ntikhoa.violapp.util.ViewUtil.setBackgroundDrawable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SampleSoundFragment : Fragment(R.layout.fragment_sample_sound),
    ChooseScaleFragment.OnItemClickListener,
    OnHoldBtnNoteListener.OnHoldCallback {

    private var _binding: FragmentSampleSoundBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapButtonNote: MapButtonNote

    private lateinit var currentScale: Scale

    private var noteBtnList = ArrayList<ArrayList<ImageButton>>()

    @Inject
    lateinit var onHoldBtnNoteListener: OnHoldBtnNoteListener

    @Inject
    lateinit var sharedPref: SampleSoundSharedPref

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

        currentScale = sharedPref.getScale()
        setBtnNoteByScale(currentScale)

        onHoldBtnNoteListener.onHoldCallback = this@SampleSoundFragment
        setStringOnClick()
        setOnBtnScaleClick()
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
                    str[j].setOnTouchListener(onHoldBtnNoteListener)
                }
            }
        }
    }

    private fun setOnBtnScaleClick() {
        binding.btnScale.setOnClickListener {
            val fragment = ChooseScaleFragment.newInstance(currentScale)
            childFragmentManager.commit {
                setCustomAnimations(0, 0, 0, R.anim.scrold_up)
                replace(R.id.fragment_container_choose_scale, fragment)
                addToBackStack(null)
            }
            fragment.onItemClickListener = this
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(scale: Scale, index: Int) {
        this.currentScale = scale
        sharedPref.saveScale(index)
        setBtnNoteByScale(scale)
    }

    private fun setBtnNoteByScale(scale: Scale) {
        binding.textViewScale.text = scale.name

        for (i in noteBtnList.indices) {
            val strList = scale.getStrListByIndex(i)
            for (j in noteBtnList[i].indices) {
                if (!strList?.contains(noteBtnList[i][j].id)!!)
                    noteBtnList[i][j].visibility = INVISIBLE
                else noteBtnList[i][j].visibility = VISIBLE
            }
        }
    }

    override fun onHold(v: View?) {
        val note = mapButtonNote.maps[v as ImageButton]
        note?.let {
            val mediaPlayer = note.getMediaPlayer(requireContext())
            binding.apply {
                if (note.isOpenStrNote)
                    v.setBackgroundDrawable(R.drawable.btn_note_square_pressed)
                else v.setBackgroundDrawable(R.drawable.btn_note_round_pressed)
                imageNote.setImageResource(note.imagesResId)
                textViewNote.text = note.name
            }
            mediaPlayer.start()
        }
    }

    override fun onRelease(v: View?) {
        val note = mapButtonNote.maps[v as ImageButton]
        note?.let {
            if (note.isOpenStrNote)
                v.setBackgroundDrawable(R.drawable.btn_note_square)
            else v.setBackgroundDrawable(R.drawable.btn_note_round)
            val mediaPlayer = note.getMediaPlayer(requireContext())
            mediaPlayer.stop()
        }
    }
}