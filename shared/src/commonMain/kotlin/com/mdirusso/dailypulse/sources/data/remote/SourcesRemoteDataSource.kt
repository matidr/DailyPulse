package com.mdirusso.dailypulse.sources.data.remote

import com.mdirusso.dailypulse.sources.data.dto.SourceDto

interface SourcesRemoteDataSource {

    suspend fun fetchSources(): List<SourceDto>
}