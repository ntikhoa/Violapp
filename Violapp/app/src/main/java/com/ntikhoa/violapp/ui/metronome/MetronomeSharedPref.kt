package com.ntikhoa.violapp.ui.metronome

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.ntikhoa.violapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MetronomeSharedPref @Inject
constructor(
    @ApplicationContext val context: Context,
    private val sharedPref: SharedPreferences
) {

    private val DEFAULT_TIME_SIGNATURE = 2

    fun saveTempo(tempo: Int) {
        sharedPref.edit()
            .putInt(context.getString(R.string.shared_tempo), tempo)
            .apply()
    }

    fun saveTimeSignature(timeSignature: Int) {
        sharedPref.edit()
            .putInt(context.getString(R.string.shared_time_signature), timeSignature)
            .apply()
    }

    fun saveMuteState(isMuted: Boolean) {
        sharedPref.edit()
            .putBoolean(context.getString(R.string.shared_mute_state), isMuted)
            .apply()
    }

    fun getTempo() =
        sharedPref.getInt(context.getString(R.string.shared_tempo), -1)

    fun getTimeSignature() =
        sharedPref.getInt(context.getString(R.string.shared_time_signature), DEFAULT_TIME_SIGNATURE)

    fun getSavedMuteState() =
        sharedPref.getBoolean(context.getString(R.string.shared_mute_state), false)
}