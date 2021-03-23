package com.ntikhoa.violapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class TickFragment(private val resLayoutId: Int) : Fragment(resLayoutId) {

    lateinit var buttons: List<ToggleButton>

    var isPlayed = MutableLiveData<Boolean>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttons = TickFragmentButtonFactory().createButtonFromFragment(resLayoutId, view)

        val repository = Repository(requireContext(), buttons)
        isPlayed.observe(viewLifecycleOwner, {
            if (it)
                repository.start()
            else repository.cancelMetronome()
        })
    }
}