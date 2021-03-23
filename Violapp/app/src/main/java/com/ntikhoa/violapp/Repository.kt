package com.ntikhoa.violapp

import android.content.Context
import android.media.MediaPlayer
import android.widget.ToggleButton
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class Repository(
    val context: Context,
    val buttons: List<ToggleButton>
) {

    private val tickSound = MediaPlayer.create(context, R.raw.metronome_tick)
    private var count = buttons.size
    private val index get() = count % buttons.size
    private val lastIndex get() = (count - 1) % buttons.size

    private lateinit var job: Job

    fun start() {
        job = Job()
        CoroutineScope(IO + job).launch {
            while (true) {
                async {
                    tickSound.start()
                    tickButton()
                    delay(1000)
                }.await()
            }
        }
    }

    fun tickButton() {
        CoroutineScope(Main).launch {
            buttons[lastIndex].isChecked = false
            buttons[index].isChecked = true
            count++
        }
    }

    fun resetMetronome() {
        CoroutineScope(Main).launch {
            buttons[lastIndex].isChecked = false
            count = buttons.size
        }
    }

    fun cancelMetronome() {
        if (this::job.isInitialized) {
            job.cancel()
            resetMetronome()
        }
    }
}