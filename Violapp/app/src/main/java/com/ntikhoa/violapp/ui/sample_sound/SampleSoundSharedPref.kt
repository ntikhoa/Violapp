package com.ntikhoa.violapp.ui.sample_sound

import android.content.Context
import android.content.SharedPreferences
import com.ntikhoa.violapp.R
import com.ntikhoa.violapp.model.Scale.Scale
import com.ntikhoa.violapp.model.Scale.ScaleList
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleSoundSharedPref
@Inject
constructor(
    @ApplicationContext val context: Context,
    private val sharedPref: SharedPreferences
) {

    fun saveScale(scaleIndex: Int) {
        sharedPref.edit()
            .putInt(context.getString(R.string.shared_scale), scaleIndex)
            .apply()
    }

    fun getScale(): Scale {
        val index = sharedPref.getInt(context.getString(R.string.shared_scale), 0)
        return ScaleList.scales[index]
    }
}