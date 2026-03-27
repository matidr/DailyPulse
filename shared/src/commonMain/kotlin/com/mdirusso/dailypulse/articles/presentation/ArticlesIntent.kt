package com.mdirusso.dailypulse.articles.presentation

import com.mdirusso.dailypulse.common.presentation.ViewIntent

sealed class ArticlesIntent : ViewIntent {
    data object GetArticles : ArticlesIntent()
    data object RefreshArticles : ArticlesIntent()
    data object ShowAbout : ArticlesIntent()
}
