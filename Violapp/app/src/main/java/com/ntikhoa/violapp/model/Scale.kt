package com.ntikhoa.violapp.model

abstract class Scale() {

    protected abstract var GstrList: List<Int>
    protected abstract var DstrList: List<Int>
    protected abstract var AstrList: List<Int>
    protected abstract var EstrList: List<Int>

    fun getStrListByIndex(index: Int): List<Int>? {
        return when (index) {
            0 -> GstrList
            1 -> DstrList
            2 -> AstrList
            3 -> EstrList
            else -> null
        }
    }
}