package com.mdirusso.dailypulse.articles.presentation

import com.mdirusso.dailypulse.articles.domain.models.Article
import com.mdirusso.dailypulse.common.presentation.ViewState

sealed class ArticlesState : ViewState {

    data class Success(val articles: List<Article> = emptyList(), val isRefreshing: Boolean) :
        ArticlesState()

    data class Error(val errorMessage: String = "") : ArticlesState()
}