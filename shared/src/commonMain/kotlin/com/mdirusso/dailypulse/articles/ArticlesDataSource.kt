package com.mdirusso.dailypulse.articles

import mdirusso.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticleDto> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleDto).executeAsList()

    fun insertArticles(articles: List<ArticleDto>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { article ->
                insertArticle(article)
            }
        }
    }

    fun clearArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()

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