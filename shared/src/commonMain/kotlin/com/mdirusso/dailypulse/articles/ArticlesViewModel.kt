package com.mdirusso.dailypulse.articles

import com.mdirusso.dailypulse.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticlesViewModel(private val useCase: ArticlesUseCase) : BaseViewModel() {

    private val _articlesState =
        MutableStateFlow<ArticlesState>(ArticlesState.Success(emptyList(), true))
    val articlesState: StateFlow<ArticlesState>
        get() = _articlesState

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) = scope.launch {
        _articlesState.update {
            (articlesState.value as? ArticlesState.Success)?.copy(isRefreshing = true) ?: it
        }
        val fetchedArticles = useCase.getArticles(forceFetch)
        _articlesState.update { ArticlesState.Success(fetchedArticles, false) }
    }
}
