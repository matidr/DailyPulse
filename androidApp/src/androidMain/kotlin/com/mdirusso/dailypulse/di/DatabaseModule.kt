package com.mdirusso.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.mdirusso.dailypulse.db.DatabaseDriverFactory
import mdirusso.dailypulse.db.DailyPulseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}