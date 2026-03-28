package com.mdirusso.dailypulse.sources.domain

import mdirusso.dailypulse.db.Source

class SourcesUseCase(private val repository: SourcesRepository) {

    suspend fun getAllSources(forceFetch: Boolean): List<Source> {
        return repository.getSources(forceFetch)
    }
}