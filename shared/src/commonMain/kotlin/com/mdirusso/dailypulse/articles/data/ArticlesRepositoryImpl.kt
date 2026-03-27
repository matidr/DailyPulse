package com.mdirusso.dailypulse.articles.data

import com.mdirusso.dailypulse.articles.data.dto.ArticleDto
import com.mdirusso.dailypulse.articles.data.local.ArticlesLocalDataSource
import com.mdirusso.dailypulse.articles.data.remote.ArticlesRemoteDataSource
import com.mdirusso.dailypulse.articles.domain.ArticlesRepository
import com.mdirusso.dailypulse.articles.domain.models.Article

class ArticlesRepositoryImpl(
    private val localDataSource: ArticlesLocalDataSource,
    private val remoteDataSource: ArticlesRemoteDataSource
) : ArticlesRepository {

    override suspend fun getArticles(forceFetch: Boolean): List<Article> {
        return if (forceFetch) {
            localDataSource.clearArticles()
            fetchArticles()
        } else {
            val cached = localDataSource.getAllArticles()
            val articles = cached.ifEmpty { fetchArticles() }
            articles
        }.map { it.toDomain() }
    }

    private suspend fun fetchArticles(): List<ArticleDto> {
        val fetchedArticles = remoteDataSource.fetchArticles()
        localDataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}