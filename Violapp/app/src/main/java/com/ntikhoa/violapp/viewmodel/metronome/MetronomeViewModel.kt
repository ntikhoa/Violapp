package com.ntikhoa.violapp.viewmodel.metronome

import androidx.lifecycle.ViewModel
import com.ntikhoa.violapp.backend_service.MetronomeService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MetronomeViewModel
@Inject
constructor(private val service: MetronomeService) : ViewModel(),
    MetronomeService.OnStartMetronomeListener,
    MetronomeService.OnCancelMetronomeListener {

    init {
        service.onStartMetronomeListener = this
        service.onCancelMetronomeListener = this
    }

    var onStartMetronomeListener: OnStartMetronomeListener? = null
    var onCancelMetronomeListener: OnCancelMetronomeListener? = null

    fun start(tempo: Int) = service.start(tempo)
    fun cancel() = service.cancel()
    fun mute() = service.mute()
    fun unmute() = service.unmute()

    interface OnStartMetronomeListener {
        fun onStartMetronome()
    }

    interface OnCancelMetronomeListener {
        fun onCancelMetronome()
    }

    override fun onStartMetronome() {
        if (onStartMetronomeListener != null) {
            onStartMetronomeListener?.onStartMetronome()
        }
    }

    override fun onCancelMetronome() {
        if (onCancelMetronomeListener != null)
            onCancelMetronomeListener?.onCancelMetronome()
    }
}