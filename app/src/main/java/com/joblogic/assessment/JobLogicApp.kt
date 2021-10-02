package com.joblogic.assessment

import android.app.Application
import com.joblogic.assessment.di.databaseModule
import com.joblogic.assessment.di.networkModule
import com.joblogic.assessment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class JobLogicApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin{
            // set log level to error due to crash in kotlin 1.5 with koin https://github.com/InsertKoinIO/koin/issues/1076
            androidLogger(Level.ERROR)
            androidContext(this@JobLogicApp)
            modules(listOf(
                networkModule,
                viewModelModule,
                databaseModule))
        }
    }
}