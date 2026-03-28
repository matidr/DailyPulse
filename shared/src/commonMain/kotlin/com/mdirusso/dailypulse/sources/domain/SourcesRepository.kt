package com.mdirusso.dailypulse.sources.domain

import com.mdirusso.dailypulse.sources.domain.models.Source

interface SourcesRepository {

    suspend fun getSources(forceFetch: Boolean): List<Source>
}