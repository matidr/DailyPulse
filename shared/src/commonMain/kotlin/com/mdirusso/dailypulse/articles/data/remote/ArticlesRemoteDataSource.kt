package com.mdirusso.dailypulse.articles.data.remote

import com.mdirusso.dailypulse.articles.data.dto.ArticleDto

interface ArticlesRemoteDataSource {
    suspend fun fetchArticles(): List<ArticleDto>
}
