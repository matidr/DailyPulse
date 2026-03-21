package com.mdirusso.dailypulse.articles

import com.mdirusso.dailypulse.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(private val useCase: ArticlesUseCase) : BaseViewModel() {

    private val _articlesState = MutableStateFlow<ArticlesState>(ArticlesState.Loading)
    val articlesState: StateFlow<ArticlesState>
        get() = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() = scope.launch {
        val fetchedArticles = useCase.getArticles()
        _articlesState.emit(ArticlesState.Success(fetchedArticles))
    }
}
