package com.ntikhoa.violapp.ui.metronome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import com.ntikhoa.violapp.backend_service.Repository
import com.ntikhoa.violapp.factory.TickFragmentButtonFactory

class TickFragment(private val resLayoutId: Int) : Fragment(resLayoutId) {

    lateinit var buttons: List<ToggleButton>

    var isMuted = MutableLiveData<Boolean>()
    var isPlayed = MutableLiveData<Boolean>()
    var tempo: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttons = TickFragmentButtonFactory().createButtonFromFragment(resLayoutId, view)

        val repository = Repository(requireContext(), buttons)
        isPlayed.observe(viewLifecycleOwner, {
            if (it && tempo != -1)
                repository.start(tempo)
            else repository.cancelMetronome()
        })

        isMuted.observe(viewLifecycleOwner, {
            if (it)
                repository.mute()
            else repository.unmute()
        })
    }
}