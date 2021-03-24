package com.ntikhoa.violapp.backend_service

import android.content.Context
import android.media.MediaPlayer
import android.widget.ToggleButton
import com.ntikhoa.violapp.R
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.math.ln

class Repository(
    val context: Context,
    private val buttons: List<ToggleButton>
) {

    private val tickSound = MediaPlayer.create(context, R.raw.metronome_tick)
    private var count = buttons.size
    private val index get() = count % buttons.size
    private val lastIndex get() = (count - 1) % buttons.size

    private lateinit var job: Job

    fun start(tempo: Int) {
        job = Job()
        val delayFrequency = calculateDelayFrequency(tempo)
        CoroutineScope(IO + job).launch {
            while (true) {
                async {
                    tickSound.start()
                    tickButton()
                    delay(delayFrequency)
                }.await()
            }
        }
    }

    private fun calculateDelayFrequency(tempo: Int): Long {
        //tempo = BPM: Beat per minute
        //calculate the time of one beat in milliseconds
        //one beat = 60 seconds * 1000 milliseconds / BPM
        return (60 * 1000 / tempo).toLong()
    }

    private fun tickButton() {
        CoroutineScope(Main).launch {
            buttons[lastIndex].isChecked = false
            buttons[index].isChecked = true
            count++
        }
    }

    fun cancelMetronome() {
        if (this::job.isInitialized) {
            job.cancel()
            resetMetronome()
        }
    }

    private fun resetMetronome() {
        CoroutineScope(Main).launch {
            buttons[lastIndex].isChecked = false
            count = buttons.size
        }
    }

    fun mute() {
        tickSound.setVolume(0f, 0f)
    }

    fun unmute() {
        val maxVolume = 100
        val volume = (ln(maxVolume.toDouble()) / ln(maxVolume.toDouble())).toFloat()
        tickSound.setVolume(volume, volume)
    }
}