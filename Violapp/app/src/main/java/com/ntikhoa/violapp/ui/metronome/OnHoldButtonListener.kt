package com.ntikhoa.violapp.ui.metronome

import android.view.MotionEvent
import android.view.View
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OnHoldButtonListener @Inject constructor() : View.OnTouchListener {

    private lateinit var job: Job

    var onHoldBtnCallback: OnHoldBtnCallback? = null

    companion object {
        private const val BTN_INCR_DECR_DELAY = 100L
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                onHoldBtnIncrDecr(v)
            }

            MotionEvent.ACTION_UP -> {
                job.cancel()
            }
        }
        return true
    }

    private fun onHoldBtnIncrDecr(v: View?) {
        job = Job()
        CoroutineScope(Dispatchers.Main + job).launch {
            while (true) {
                if (onHoldBtnCallback != null) {
                    onHoldBtnCallback?.onHold(v)
                    delay(BTN_INCR_DECR_DELAY)
                }
            }
        }
    }

    interface OnHoldBtnCallback {
        fun onHold(v: View?)
    }
}