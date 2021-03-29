package com.ntikhoa.violapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.media.MediaPlayer
import com.ntikhoa.violapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesSharedPref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.file_metronome), MODE_PRIVATE)

    @Provides
    fun providesTickSound(@ApplicationContext context: Context): MediaPlayer =
        MediaPlayer.create(context, R.raw.metronome_tick)
}