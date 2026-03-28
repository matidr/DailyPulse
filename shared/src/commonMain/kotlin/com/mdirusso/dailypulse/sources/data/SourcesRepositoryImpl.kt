package com.mdirusso.dailypulse.sources.data

import com.mdirusso.dailypulse.sources.data.dto.SourceDto
import com.mdirusso.dailypulse.sources.data.local.SourcesLocalDataSource
import com.mdirusso.dailypulse.sources.data.remote.SourcesRemoteDataSource
import com.mdirusso.dailypulse.sources.domain.SourcesRepository
import com.mdirusso.dailypulse.sources.domain.models.Source

class SourcesRepositoryImpl(
    private val localDataSource: SourcesLocalDataSource,
    private val remoteDataSource: SourcesRemoteDataSource
) : SourcesRepository {

    override suspend fun getSources(forceFetch: Boolean): List<Source> {
        return if (forceFetch) {
            localDataSource.clearSources()
            fetchSources()
        } else {
            val cached = localDataSource.getAllSources()
            val articles = cached.ifEmpty { fetchSources() }
            articles
        }.map { it.toDomain() }
    }

    private suspend fun fetchSources(): List<SourceDto> {
        val fetchedSources = remoteDataSource.fetchSources()
        localDataSource.insertSources(fetchedSources)
        return fetchedSources
    }
}