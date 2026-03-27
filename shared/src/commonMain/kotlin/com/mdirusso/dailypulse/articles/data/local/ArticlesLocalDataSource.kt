package com.mdirusso.dailypulse.articles.data.local

import com.mdirusso.dailypulse.articles.data.dto.ArticleDto

interface ArticlesLocalDataSource {
    fun getAllArticles(): List<ArticleDto>
    fun insertArticles(articles: List<ArticleDto>)
    fun clearArticles()
}
