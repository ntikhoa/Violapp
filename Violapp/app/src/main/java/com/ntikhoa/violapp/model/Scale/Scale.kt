package com.ntikhoa.violapp.model.Scale

abstract class Scale() {

    abstract val name: String
    abstract val imageResId: Int

    protected abstract val GstrList: List<Int>
    protected abstract val DstrList: List<Int>
    protected abstract val AstrList: List<Int>
    protected abstract val EstrList: List<Int>

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