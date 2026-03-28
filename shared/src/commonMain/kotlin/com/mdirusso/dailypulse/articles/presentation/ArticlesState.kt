package com.mdirusso.dailypulse.articles.presentation

import com.mdirusso.dailypulse.common.presentation.ViewState

sealed class ArticlesState : ViewState {

    data class Success(val articles: List<ArticleUiModel> = emptyList(), val isRefreshing: Boolean) :
        ArticlesState()

    data class Error(val errorMessage: String = "") : ArticlesState()
}