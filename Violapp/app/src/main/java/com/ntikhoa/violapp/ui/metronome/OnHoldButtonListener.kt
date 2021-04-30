package com.ntikhoa.violapp.ui.metronome

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.util.ViewUtil.scaleDownOnRelease
import com.ntikhoa.violapp.util.ViewUtil.scaleUpOnTouch
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OnHoldButtonListener @Inject constructor() :
    View.OnTouchListener {

    private lateinit var job: Job

    private var onDown = false

    var onHoldBtnCallback: OnHoldBtnCallback? = null

    companion object {
        private const val BTN_INCR_DECR_DELAY = 100L
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!onDown) {
                    onDown = true
                    v?.scaleUpOnTouch()
                }
                onHoldBtnIncrDecr(v)
            }

            MotionEvent.ACTION_UP -> {
                onDown = false
                v?.scaleDownOnRelease()
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