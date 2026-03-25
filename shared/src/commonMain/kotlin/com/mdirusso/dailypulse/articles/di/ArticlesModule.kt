package com.mdirusso.dailypulse.articles.di

import com.mdirusso.dailypulse.articles.ArticlesDataSource
import com.mdirusso.dailypulse.articles.ArticlesRepository
import com.mdirusso.dailypulse.articles.ArticlesService
import com.mdirusso.dailypulse.articles.ArticlesUseCase
import com.mdirusso.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

fun articlesModule(articlesServiceApiKey: String) = module {
    single<ArticlesService> { ArticlesService(get(), articlesServiceApiKey) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    factory { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
}
