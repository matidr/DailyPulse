package com.mdirusso.dailypulse

import android.app.Application
import com.mdirusso.dailypulse.di.databaseModule
import com.mdirusso.dailypulse.di.sharedModules
import com.mdirusso.dailypulse.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DailyPulseApp)
            modules(sharedModules(Secrets.newsApiKey) + viewModelsModule + databaseModule)
        }
    }
}
