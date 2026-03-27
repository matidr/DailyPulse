package com.mdirusso.dailypulse.articles.domain

import com.mdirusso.dailypulse.articles.domain.models.Article

class ArticlesUseCase(private val repository: ArticlesRepository) {

    suspend fun getArticles(forceFetch: Boolean): List<Article> {
        return repository.getArticles(forceFetch)
    }
}