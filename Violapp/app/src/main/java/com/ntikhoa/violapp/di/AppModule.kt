package com.ntikhoa.violapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesSharedPref(@ApplicationContext context: Context) =
        context.getSharedPreferences("cc gi cung dc", MODE_PRIVATE)
}