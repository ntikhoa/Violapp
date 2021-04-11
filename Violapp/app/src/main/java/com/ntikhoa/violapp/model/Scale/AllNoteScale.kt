package com.ntikhoa.violapp.model.Scale


import com.ntikhoa.violapp.R
import kotlinx.parcelize.Parcelize


@Parcelize
class AllNoteScale : Scale() {

    override val name = "All notes"
    override val imageResId = R.drawable.c_major_scale

    override val GstrList: List<Int> = arrayListOf(
        R.id.btn0,
        R.id.btn1,
        R.id.btn2,
        R.id.btn3,
        R.id.btn4,
        R.id.btn5,
        R.id.btn6,
        R.id.btn7
    )

    override val DstrList = GstrList

    override val AstrList = GstrList

    override val EstrList = GstrList
}