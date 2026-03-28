package com.mdirusso.dailypulse.sources.data.local

import com.mdirusso.dailypulse.sources.data.dto.SourceDto

interface SourcesLocalDataSource {
    fun getAllSources(): List<SourceDto>
    fun insertSources(sources: List<SourceDto>)
    fun clearSources()
}