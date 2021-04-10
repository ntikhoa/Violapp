package com.ntikhoa.violapp.model.Scale

class ScaleList {

    companion object {
        val scales = arrayListOf(
            AllNoteScale(),
            AmajorScale(),
            CmajorScale(),
            DmajorScale()
        )
    }
}