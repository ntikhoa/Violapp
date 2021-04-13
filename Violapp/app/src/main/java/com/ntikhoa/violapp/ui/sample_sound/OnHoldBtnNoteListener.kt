package com.ntikhoa.violapp.ui.sample_sound

import android.view.MotionEvent
import android.view.View
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnHoldBtnNoteListener @Inject constructor() : View.OnTouchListener {

    var onHoldCallback: OnHoldCallback? = null

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN ->
                onHoldCallback?.onHold(v)

            MotionEvent.ACTION_UP ->
                onHoldCallback?.onRelease(v)
        }
        return true
    }


    interface OnHoldCallback {
        fun onHold(v: View?)
        fun onRelease(v: View?)
    }
}