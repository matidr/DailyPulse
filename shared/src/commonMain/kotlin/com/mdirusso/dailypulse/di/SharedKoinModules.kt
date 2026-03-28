package com.mdirusso.dailypulse.di

import com.mdirusso.dailypulse.articles.di.articlesModule
import com.mdirusso.dailypulse.articles.di.sourcesModule
import org.koin.core.module.Module

fun sharedModules(articlesServiceApiKey: String): List<Module> = listOf(
    networkModule, articlesModule(articlesServiceApiKey),
    sourcesModule(articlesServiceApiKey)
)
