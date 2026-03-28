package com.mdirusso.dailypulse.sources.presentation

import com.mdirusso.dailypulse.common.presentation.ViewState

sealed class SourcesState : ViewState {

    data class Success(val sources: List<SourceUiModel> = emptyList(), val isRefreshing: Boolean) :
        SourcesState()

    data class Error(val errorMessage: String = "") : SourcesState()
}