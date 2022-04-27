package com.example.sampleui.di

import android.app.Application
import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 07/04/2022 5:45 PM
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext;
    }
}