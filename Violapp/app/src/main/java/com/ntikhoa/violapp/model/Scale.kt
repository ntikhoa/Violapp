package com.ntikhoa.violapp.model

interface Scale {
    fun getGstrList(): List<Int>
    fun getDstrList(): List<Int>
    fun getAstrList(): List<Int>
    fun getEstrList(): List<Int>
}