package com.mdirusso.dailypulse.sources.presentation

import com.mdirusso.dailypulse.common.presentation.ViewIntent

sealed class SourcesIntent : ViewIntent {
    data object OnScreenLoaded : SourcesIntent()
    data object OnRefreshSwiped : SourcesIntent()
}
