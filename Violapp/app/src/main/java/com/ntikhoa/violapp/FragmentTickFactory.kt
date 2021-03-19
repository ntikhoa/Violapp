package com.ntikhoa.violapp

import android.view.View
import android.widget.ToggleButton
import com.ntikhoa.violapp.databinding.Fragment2TickBinding
import com.ntikhoa.violapp.databinding.Fragment3TickBinding

class FragmentTickFactory {

    fun createButtonFromFragment(layoutResId: Int, root: View): List<ToggleButton> {
        val buttons = ArrayList<ToggleButton>()
        when (layoutResId) {
            R.layout.fragment_2_tick -> {
                val binding = Fragment2TickBinding.bind(root)
                buttons.add(binding.btn1.btnTick)
                buttons.add(binding.btn2.btnTick)
            }
            R.layout.fragment_3_tick -> {
                val binding = Fragment3TickBinding.bind(root)
                buttons.add(binding.btn1.btnTick)
                buttons.add(binding.btn2.btnTick)
                buttons.add(binding.btn3.btnTick)
            }
        }
        return buttons
    }
}