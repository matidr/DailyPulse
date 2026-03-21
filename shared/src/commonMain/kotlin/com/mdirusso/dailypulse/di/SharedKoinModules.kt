package com.mdirusso.dailypulse.di

import com.mdirusso.dailypulse.articles.di.articlesModule
import org.koin.core.module.Module

fun sharedModules(articlesServiceApiKey: String): List<Module> = listOf(networkModule, articlesModule(articlesServiceApiKey))
