package com.mdirusso.dailypulse.articles.di

import com.mdirusso.dailypulse.sources.data.SourcesRepositoryImpl
import com.mdirusso.dailypulse.sources.data.local.SourcesLocalDataSource
import com.mdirusso.dailypulse.sources.data.local.SourcesLocalDataSourceImpl
import com.mdirusso.dailypulse.sources.data.remote.SourcesRemoteDataSource
import com.mdirusso.dailypulse.sources.data.remote.SourcesRemoteDataSourceImpl
import com.mdirusso.dailypulse.sources.domain.SourcesRepository
import com.mdirusso.dailypulse.sources.domain.SourcesUseCase
import com.mdirusso.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

fun sourcesModule(articlesServiceApiKey: String) = module {
    single<SourcesRemoteDataSource> { SourcesRemoteDataSourceImpl(get(), articlesServiceApiKey) }
    single<SourcesLocalDataSource> { SourcesLocalDataSourceImpl(get()) }
    single<SourcesRepository> { SourcesRepositoryImpl(get(), get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    factory { SourcesViewModel(get()) }
}