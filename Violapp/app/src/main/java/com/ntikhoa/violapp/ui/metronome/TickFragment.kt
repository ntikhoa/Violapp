package com.ntikhoa.violapp.ui.metronome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import com.ntikhoa.violapp.backend_service.MetronomeService
import com.ntikhoa.violapp.factory.TickFragmentButtonFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TickFragment(private val resLayoutId: Int) : Fragment(resLayoutId),
    MetronomeService.OnStartMetronomeListener,
    MetronomeService.OnCancelMetronomeListener {

    private lateinit var buttons: List<ToggleButton>

    var isMuted = MutableLiveData<Boolean>()
    var isPlayed = MutableLiveData<Boolean>()
    var tempo: Int = -1

    private var count = -1
    private val index get() = count % buttons.size
    private val lastIndex get() = (count - 1) % buttons.size

    @Inject
    lateinit var metronomeService: MetronomeService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent(view)

        metronomeService.onCancelMetronomeListener = this
        metronomeService.onStartMetronomeListener = this

        isPlayed.observe(viewLifecycleOwner, {
            if (it && tempo != -1)
                metronomeService.start(tempo)
            else metronomeService.cancelMetronome()
        })

        isMuted.observe(viewLifecycleOwner, {
            if (it)
                metronomeService.mute()
            else metronomeService.unmute()
        })
    }

    private fun initComponent(view: View) {
        buttons = TickFragmentButtonFactory().createButtonFromFragment(resLayoutId, view)
        count = buttons.size
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
}