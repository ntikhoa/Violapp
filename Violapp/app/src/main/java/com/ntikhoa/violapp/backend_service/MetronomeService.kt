package com.ntikhoa.violapp.backend_service

import android.media.MediaPlayer
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.ln


@Singleton
class MetronomeService @Inject constructor(private val tickSound: MediaPlayer) {

    private lateinit var job: Job

    var onStartMetronomeListener: OnStartMetronomeListener? = null
    var onCancelMetronomeListener: OnCancelMetronomeListener? = null

    fun start(tempo: Int) {
        job = Job()
        val delayFrequency = calculateDelayFrequency(tempo)
        CoroutineScope(IO + job).launch {
            while (true) {
                async {
                    tickSound.start()
                    if (onStartMetronomeListener != null) {
                        CoroutineScope(Dispatchers.Main).launch {
                            onStartMetronomeListener?.onStartMetronome()
                        }
                    }
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

    fun cancel() {
        if (this::job.isInitialized) {
            job.cancel()
            if (onCancelMetronomeListener != null) {
                CoroutineScope(Dispatchers.Main).launch {
                    onCancelMetronomeListener?.onCancelMetronome()
                }
            }
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

    interface OnStartMetronomeListener {
        fun onStartMetronome()
    }

    interface OnCancelMetronomeListener {
        fun onCancelMetronome()
    }
}