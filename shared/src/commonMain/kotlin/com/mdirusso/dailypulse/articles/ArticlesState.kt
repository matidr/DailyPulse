package com.mdirusso.dailypulse.articles

sealed class ArticlesState {

    data class Success(val articles: List<Article> = emptyList(), val isRefreshing: Boolean) : ArticlesState()

    data class Error(val errorMessage: String = "") : ArticlesState()
}