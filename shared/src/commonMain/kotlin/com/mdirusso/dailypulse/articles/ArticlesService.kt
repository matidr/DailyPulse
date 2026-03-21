package com.mdirusso.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient, private val apiKey: String) {

    private val country = "us"
    private val category = "business"

    suspend fun fetchArticles(): List<ArticleDto> {
        val response: ArticlesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body()

        return response.articles.orEmpty()
    }
}