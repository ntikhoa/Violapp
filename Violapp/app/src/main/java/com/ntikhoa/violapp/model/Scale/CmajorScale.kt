package com.ntikhoa.violapp.model.Scale

import com.ntikhoa.violapp.R

class CmajorScale : Scale() {

    override val name = "C major Scale"
    override val imageResId = R.drawable.c_major_scale

    override val GstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn2,
        R.id.btn4,
        R.id.btn5,
        R.id.btn7
    )

    override val DstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn2,
        R.id.btn3,
        R.id.btn5,
        R.id.btn7
    )

    override val AstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn2,
        R.id.btn3,
        R.id.btn5,
        R.id.btn7
    )

    override val EstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn1,
        R.id.btn3,
        R.id.btn5,
        R.id.btn7
    )
}