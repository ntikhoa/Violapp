package com.ntikhoa.violapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ntikhoa.violapp.databinding.Fragment2TickBinding

class TickFragment(private val resLayoutId: Int) : Fragment(resLayoutId) {

    lateinit var buttons: List<Button>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttons = FragmentTickFactory().createButtonFromFragment(resLayoutId, view)
        println("debug: number of button ${buttons.size}")
    }
}