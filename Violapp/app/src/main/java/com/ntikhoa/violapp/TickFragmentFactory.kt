package com.ntikhoa.violapp


class TickFragmentFactory {

    fun createTickFragment(timeSignature: Int): TickFragment? {
        when (timeSignature) {
            2 -> return TickFragment(R.layout.fragment_2_tick)
            3 -> return TickFragment(R.layout.fragment_3_tick)
            4 -> return TickFragment(R.layout.fragment_4_tick)
            6 -> return TickFragment(R.layout.fragment_6_tick)
            9 -> return TickFragment(R.layout.fragment_9_tick)
            else -> return null
        }
    }
}