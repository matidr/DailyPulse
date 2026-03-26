package com.mdirusso.dailypulse.articles

class ArticlesRepository(
    private val localDataSource: ArticlesDataSource,
    private val articlesService: ArticlesService
) {

    suspend fun getArticles(forceFetch: Boolean): List<ArticleDto> {
        if (forceFetch) {
            localDataSource.clearArticles()
            return fetchArticles()
        }

        val articlesDb = localDataSource.getAllArticles()

        if (articlesDb.isEmpty()) {

            return fetchArticles()
        }

        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleDto> {
        val fetchedArticles = articlesService.fetchArticles()
        localDataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}