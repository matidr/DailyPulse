package com.mdirusso.dailypulse.di

import com.mdirusso.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin(articlesServiceApiKey: String) {
    val modules = sharedModules(articlesServiceApiKey) + databaseModule
    startKoin { modules(modules) }
}

class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}