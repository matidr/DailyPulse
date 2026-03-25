package com.mdirusso.dailypulse.articles

class ArticlesRepository(
    private val localDataSource: ArticlesDataSource,
    private val articlesService: ArticlesService
) {

    suspend fun getArticles(): List<ArticleDto> {
        val articlesDb = localDataSource.getAllArticles()

        if (articlesDb.isEmpty()) {
            val fetchedArticles = articlesService.fetchArticles()
            localDataSource.insertArticles(fetchedArticles)
            return fetchedArticles
        }

        return articlesDb
    }
}