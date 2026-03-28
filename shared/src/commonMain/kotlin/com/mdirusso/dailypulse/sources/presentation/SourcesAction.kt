package com.mdirusso.dailypulse.sources.presentation

import com.mdirusso.dailypulse.common.presentation.ViewAction

sealed class SourcesAction : ViewAction {
    data class GetSources(val forceFetch: Boolean) : SourcesAction()
}
