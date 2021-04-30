package com.ntikhoa.violapp.model.note

import android.content.Context
import android.media.MediaPlayer


class Note(val imagesResId: Int, val soundResId: Int, val name: String, val isOpenStrNote: Boolean = false) {

    private lateinit var mediaPlayer: MediaPlayer

    fun getMediaPlayer(context: Context): MediaPlayer {
        if (!::mediaPlayer.isInitialized || !mediaPlayer.isPlaying) {
            mediaPlayer = MediaPlayer.create(context, soundResId)
        }
        return mediaPlayer
    }
}