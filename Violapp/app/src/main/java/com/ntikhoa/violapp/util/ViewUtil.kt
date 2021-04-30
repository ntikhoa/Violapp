package com.ntikhoa.violapp.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.ntikhoa.violapp.R

object ViewUtil {

    fun View.setBackgroundDrawable(drawableResId: Int) {
        this.background = ContextCompat.getDrawable(context, drawableResId)
    }

    fun View.scaleUpOnTouch() {
        val anim = AnimationUtils.loadAnimation(context, R.anim.scale_up)
        this.startAnimation(anim)
    }

    fun View.scaleDownOnRelease() {
        val anim = AnimationUtils.loadAnimation(context, R.anim.scale_down)
        this.startAnimation(anim)
    }
}