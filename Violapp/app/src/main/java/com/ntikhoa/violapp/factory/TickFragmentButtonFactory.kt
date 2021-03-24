package com.ntikhoa.violapp.factory

import android.view.View
import android.widget.ToggleButton
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.databinding.*

class TickFragmentButtonFactory {

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
            R.layout.fragment_4_tick -> {
                val binding = Fragment4TickBinding.bind(root)
                buttons.add(binding.btn1.btnTick)
                buttons.add(binding.btn2.btnTick)
                buttons.add(binding.btn3.btnTick)
                buttons.add(binding.btn4.btnTick)
            }

            R.layout.fragment_6_tick -> {
                val binding = Fragment6TickBinding.bind(root)
                buttons.add(binding.btn1.btnTick)
                buttons.add(binding.btn2.btnTick)
                buttons.add(binding.btn3.btnTick)
                buttons.add(binding.btn4.btnTick)
                buttons.add(binding.btn5.btnTick)
                buttons.add(binding.btn6.btnTick)
            }

            R.layout.fragment_9_tick -> {
                val binding = Fragment9TickBinding.bind(root)
                buttons.add(binding.btn1.btnTick)
                buttons.add(binding.btn2.btnTick)
                buttons.add(binding.btn3.btnTick)
                buttons.add(binding.btn4.btnTick)
                buttons.add(binding.btn5.btnTick)
                buttons.add(binding.btn6.btnTick)
                buttons.add(binding.btn7.btnTick)
                buttons.add(binding.btn8.btnTick)
                buttons.add(binding.btn9.btnTick)
            }
        }
        return buttons
    }
}