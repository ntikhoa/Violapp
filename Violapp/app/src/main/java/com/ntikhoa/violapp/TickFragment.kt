package com.ntikhoa.violapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button

class TickFragment(private val resLayoutId: Int) : Fragment(resLayoutId) {

    lateinit var buttons: List<Button>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttons = TickFragmentButtonFactory().createButtonFromFragment(resLayoutId, view)
        println("debug: number of button ${buttons.size}")
    }
}