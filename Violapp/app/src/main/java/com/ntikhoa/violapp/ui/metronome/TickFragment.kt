package com.ntikhoa.violapp.ui.metronome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ToggleButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.ntikhoa.violapp.factory.TickFragmentButtonFactory
import com.ntikhoa.violapp.viewmodel.metronome.MetronomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TickFragment(private val resLayoutId: Int) : Fragment(resLayoutId),
    MetronomeViewModel.OnStartMetronomeListener,
    MetronomeViewModel.OnCancelMetronomeListener {

    private val metronomeViewModel by viewModels<MetronomeViewModel>()

    var isMuted = MutableLiveData<Boolean>()
    var isPlayed = MutableLiveData<Boolean>()
    var tempo: Int = -1

    private lateinit var buttons: List<ToggleButton>
    private var count = -1
    private val index get() = count % buttons.size
    private val lastIndex get() = (count - 1) % buttons.size

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent(view)
        setServiceListener()
        setOnPlayStateChange()
        setOnMuteStateChange()
    }

    private fun initComponent(view: View) {
        buttons = TickFragmentButtonFactory().createButtonFromFragment(resLayoutId, view)
        count = buttons.size
    }

    private fun setServiceListener() {
        metronomeViewModel.onCancelMetronomeListener = this
        metronomeViewModel.onStartMetronomeListener = this
    }

    override fun onStartMetronome() {
        buttons[lastIndex].isChecked = false
        buttons[index].isChecked = true
        count++
    }

    override fun onCancelMetronome() {
        buttons[lastIndex].isChecked = false
        count = buttons.size
    }

    private fun setOnPlayStateChange() {
        isPlayed.observe(viewLifecycleOwner, {
            if (it && tempo != -1)
                metronomeViewModel.start(tempo)
            else metronomeViewModel.cancel()
        })
    }

    private fun setOnMuteStateChange() {
        isMuted.observe(viewLifecycleOwner, {
            if (it)
                metronomeViewModel.mute()
            else metronomeViewModel.unmute()
        })
    }
}