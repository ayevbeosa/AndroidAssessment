package com.example.android.androidassessment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AssessmentApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Enable logging in debug mode only
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}