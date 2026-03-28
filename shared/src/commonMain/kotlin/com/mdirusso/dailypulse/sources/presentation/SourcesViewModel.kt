package com.mdirusso.dailypulse.sources.presentation

import com.mdirusso.dailypulse.common.presentation.BaseViewModel
import com.mdirusso.dailypulse.sources.domain.SourcesUseCase

class SourcesViewModel(private val useCase: SourcesUseCase) :
    BaseViewModel<SourcesIntent, SourcesAction, SourcesState, SourcesEffect>(
        initialState = SourcesState.Success(isRefreshing = true)
    ) {

    init {
        dispatchIntent(SourcesIntent.GetSources)
    }

    override fun intentToAction(intent: SourcesIntent): SourcesAction = when (intent) {
        SourcesIntent.GetSources -> SourcesAction.GetSources(forceFetch = false)
        SourcesIntent.RefreshSources -> SourcesAction.GetSources(forceFetch = true)
    }

    override fun handleAction(action: SourcesAction) {
        when (action) {
            is SourcesAction.GetSources -> fetchSources(action.forceFetch)
        }
    }

    private fun fetchSources(forceFetch: Boolean) {
        launchOnUI {
            val refreshing = (state.value as? SourcesState.Success)?.copy(isRefreshing = true)
                ?: SourcesState.Success(isRefreshing = true)
            changeState(refreshing)
            runCatching { useCase.getAllSources(forceFetch) }
                .onSuccess { sources -> changeState(SourcesState.Success(sources.map { it.toUiModel() }, false)) }
                .onFailure { e -> changeState(SourcesState.Error(e.message.orEmpty())) }
        }
    }
}
