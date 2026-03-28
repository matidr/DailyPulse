package com.mdirusso.dailypulse.sources.data.remote

import com.mdirusso.dailypulse.sources.data.dto.SourceDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesRemoteDataSourceImpl(private val httpClient: HttpClient, private val apiKey: String) :
    SourcesRemoteDataSource {

    override suspend fun fetchSources(): List<SourceDto> {
        val response: SourcesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey")
                .body()

        return response.sources.orEmpty()
    }
}