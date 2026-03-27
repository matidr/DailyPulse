package com.mdirusso.dailypulse.articles.presentation

import com.mdirusso.dailypulse.common.presentation.ViewEffect

sealed class ArticlesEffect : ViewEffect {
    data object NavigateToAbout : ArticlesEffect()
}
