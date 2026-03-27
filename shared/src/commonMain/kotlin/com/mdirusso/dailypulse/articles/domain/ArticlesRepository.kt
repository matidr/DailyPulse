package com.mdirusso.dailypulse.articles.domain

import com.mdirusso.dailypulse.articles.domain.models.Article

interface ArticlesRepository {

    suspend fun getArticles(forceFetch: Boolean): List<Article>
}