package com.mdirusso.dailypulse.articles

sealed class ArticlesState {

    data class Success(val articles: List<Article> = emptyList()) : ArticlesState()

    data object Loading : ArticlesState()

    data class Error(val errorMessage: String = "") : ArticlesState()
}