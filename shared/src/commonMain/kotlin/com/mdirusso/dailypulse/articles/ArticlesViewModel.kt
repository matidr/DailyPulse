package com.mdirusso.dailypulse.articles

import com.mdirusso.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel(private val apiKey: String) : BaseViewModel() {

    private val _articlesState = MutableStateFlow<ArticlesState>(ArticlesState.Loading)
    val articlesState: StateFlow<ArticlesState>
        get() = _articlesState

    private val useCase: ArticlesUseCase

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val service = ArticlesService(httpClient, apiKey)
        useCase = ArticlesUseCase(service)
        getArticles()
    }

    private fun getArticles() = scope.launch {
        val fetchedArticles = useCase.getArticles()
        _articlesState.emit(ArticlesState.Success(fetchedArticles))
    }
}