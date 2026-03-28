package com.mdirusso.dailypulse.di

import com.mdirusso.dailypulse.articles.presentation.ArticlesViewModel
import com.mdirusso.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
    viewModel { SourcesViewModel(get()) }
}