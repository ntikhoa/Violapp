package com.ntikhoa.violapp.util

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager

object ActivityUtil {
    fun setFullScreen(activity: Activity) {
        activity.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.hide(WindowInsets.Type.statusBars())
            } else {
                @Suppress("DEPRECATION")
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
    }

}