package com.mdirusso.dailypulse.sources.presentation

import com.mdirusso.dailypulse.common.presentation.ViewState
import mdirusso.dailypulse.db.Source

sealed class SourcesState : ViewState {

    data class Success(val sources: List<Source> = emptyList(), val isRefreshing: Boolean) :
        SourcesState()

    data class Error(val errorMessage: String = "") : SourcesState()
}