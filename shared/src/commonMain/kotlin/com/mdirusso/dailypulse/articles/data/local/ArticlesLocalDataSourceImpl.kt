package com.mdirusso.dailypulse.articles.data.local

import com.mdirusso.dailypulse.articles.data.dto.ArticleDto
import mdirusso.dailypulse.db.DailyPulseDatabase

class ArticlesLocalDataSourceImpl(private val database: DailyPulseDatabase) : ArticlesLocalDataSource {

    override fun getAllArticles(): List<ArticleDto> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleDto).executeAsList()

    override fun insertArticles(articles: List<ArticleDto>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { article ->
                insertArticle(article)
            }
        }
    }

    override fun clearArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(article: ArticleDto) {
        database.dailyPulseDatabaseQueries.insertArticle(
            article.title.orEmpty(),
            article.description,
            article.date.orEmpty(),
            article.imageUrl
        )
    }

    private fun mapToArticleDto(title: String, description: String?, date: String, url: String?) =
        ArticleDto(title, description, date, url)
}