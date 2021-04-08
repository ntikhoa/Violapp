package com.ntikhoa.violapp.model

import com.ntikhoa.violapp.R

class AmajorScale() : Scale {

    private val GstrList = arrayListOf<Int>(
        R.id.btn1,
        R.id.btn2,
        R.id.btn4,
        R.id.btn6,
        R.id.btn7
    )

    private val DstrList = arrayListOf<Int>(
        R.id.btn0,
        R.id.btn2,
        R.id.btn4,
        R.id.btn6,
        R.id.btn7
    )

    private val AstrList = arrayListOf<Int>(
        R.id.btn0,
        R.id.btn2,
        R.id.btn4,
        R.id.btn5,
        R.id.btn7
    )

    private val EstrList = arrayListOf<Int>(
        R.id.btn0,
        R.id.btn2,
        R.id.btn4,
        R.id.btn5,
        R.id.btn7
    )

    override fun getGstrList() = GstrList

    override fun getDstrList() = DstrList

    override fun getAstrList() = AstrList

    override fun getEstrList() = EstrList
}