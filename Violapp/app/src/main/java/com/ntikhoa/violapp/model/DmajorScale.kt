package com.ntikhoa.violapp.model

import com.ntikhoa.violapp.R

class DmajorScale : Scale() {

    override var GstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn2,
        R.id.btn4,
        R.id.btn6,
        R.id.btn7
    )

    override var DstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn2,
        R.id.btn4,
        R.id.btn5,
        R.id.btn7
    )

    override var AstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn2,
        R.id.btn4,
        R.id.btn5,
        R.id.btn7
    )

    override var EstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn2,
        R.id.btn3,
        R.id.btn5,
        R.id.btn7
    )
}