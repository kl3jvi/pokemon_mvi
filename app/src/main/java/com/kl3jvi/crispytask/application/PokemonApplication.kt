package com.kl3jvi.crispytask.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.uniflow.android.logger.AndroidMessageLogger
import io.uniflow.core.logger.UniFlowLogger

@HiltAndroidApp
class PokemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UniFlowLogger.init(AndroidMessageLogger())
    }
}