package com.mdirusso.dailypulse.articles.presentation

import com.mdirusso.dailypulse.common.presentation.ViewIntent

sealed class ArticlesIntent : ViewIntent {
    data object OnScreenLoaded : ArticlesIntent()
    data object OnRefreshSwiped : ArticlesIntent()
    data object OnAboutClicked : ArticlesIntent()
    data object OnSourcesClicked : ArticlesIntent()
}
