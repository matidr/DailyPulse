package com.mdirusso.dailypulse.articles.presentation

import com.mdirusso.dailypulse.articles.domain.ArticlesUseCase
import com.mdirusso.dailypulse.common.presentation.BaseViewModel

class ArticlesViewModel(private val useCase: ArticlesUseCase) :
    BaseViewModel<ArticlesIntent, ArticlesAction, ArticlesState, ArticlesEffect>(
        initialState = ArticlesState.Success(isRefreshing = true)
    ) {

    init {
        dispatchIntent(ArticlesIntent.OnScreenLoaded)
    }

    override fun intentToAction(intent: ArticlesIntent): ArticlesAction = when (intent) {
        ArticlesIntent.OnScreenLoaded -> ArticlesAction.GetArticles(forceFetch = false)
        ArticlesIntent.OnRefreshSwiped -> ArticlesAction.GetArticles(forceFetch = true)
        ArticlesIntent.OnAboutClicked -> ArticlesAction.ShowAbout
        ArticlesIntent.OnSourcesClicked -> ArticlesAction.ShowSources
    }

    override fun handleAction(action: ArticlesAction) {
        when (action) {
            is ArticlesAction.GetArticles -> fetchArticles(action.forceFetch)
            ArticlesAction.ShowAbout -> triggerEffect(ArticlesEffect.NavigateToAbout)
            ArticlesAction.ShowSources -> triggerEffect(ArticlesEffect.NavigateToSources)
        }
    }

    private fun fetchArticles(forceFetch: Boolean) {
        launchOnUI {
            val refreshing = (state.value as? ArticlesState.Success)?.copy(isRefreshing = true)
                ?: ArticlesState.Success(isRefreshing = true)
            changeState(refreshing)
            runCatching { useCase.getArticles(forceFetch) }
                .onSuccess { articles -> changeState(ArticlesState.Success(articles.map { it.toUiModel() }, false)) }
                .onFailure { e -> changeState(ArticlesState.Error(e.message.orEmpty())) }
        }
    }
}
