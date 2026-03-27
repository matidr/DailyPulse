package com.mdirusso.dailypulse.articles.di

import com.mdirusso.dailypulse.articles.data.ArticlesRepositoryImpl
import com.mdirusso.dailypulse.articles.domain.ArticlesRepository
import com.mdirusso.dailypulse.articles.data.local.ArticlesLocalDataSource
import com.mdirusso.dailypulse.articles.data.local.ArticlesLocalDataSourceImpl
import com.mdirusso.dailypulse.articles.data.remote.ArticlesRemoteDataSource
import com.mdirusso.dailypulse.articles.data.remote.ArticlesRemoteDataSourceImpl
import com.mdirusso.dailypulse.articles.domain.ArticlesUseCase
import com.mdirusso.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

fun articlesModule(articlesServiceApiKey: String) = module {
    single<ArticlesRemoteDataSource> { ArticlesRemoteDataSourceImpl(get(), articlesServiceApiKey) }
    single<ArticlesLocalDataSource> { ArticlesLocalDataSourceImpl(get()) }
    single<ArticlesRepository> { ArticlesRepositoryImpl(get(), get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    factory { ArticlesViewModel(get()) }
}
