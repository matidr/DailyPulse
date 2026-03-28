package com.mdirusso.dailypulse.sources.domain

import com.mdirusso.dailypulse.sources.domain.models.Source

class SourcesUseCase(private val repository: SourcesRepository) {

    suspend fun getAllSources(forceFetch: Boolean): List<Source> {
        return repository.getSources(forceFetch)
    }
}