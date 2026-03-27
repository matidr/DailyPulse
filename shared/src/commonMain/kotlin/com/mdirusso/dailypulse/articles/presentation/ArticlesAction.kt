package com.mdirusso.dailypulse.articles.presentation

import com.mdirusso.dailypulse.common.presentation.ViewAction

sealed class ArticlesAction : ViewAction {
    data class GetArticles(val forceFetch: Boolean) : ArticlesAction()
    data object ShowAbout : ArticlesAction()
}
