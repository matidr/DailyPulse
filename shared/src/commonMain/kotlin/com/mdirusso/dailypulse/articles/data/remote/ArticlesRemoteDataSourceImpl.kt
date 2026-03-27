package com.mdirusso.dailypulse.articles.data.remote

import com.mdirusso.dailypulse.articles.data.dto.ArticleDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.collections.orEmpty

class ArticlesRemoteDataSourceImpl(private val httpClient: HttpClient, private val apiKey: String) :
    ArticlesRemoteDataSource {

    private val country = "us"
    private val category = "business"

    override suspend fun fetchArticles(): List<ArticleDto> {
        val response: ArticlesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body()

        return response.articles.orEmpty()
    }
}