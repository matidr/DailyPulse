package com.mdirusso.dailypulse.sources.data.local

import com.mdirusso.dailypulse.sources.data.dto.SourceDto
import mdirusso.dailypulse.db.DailyPulseDatabase

class SourcesLocalDataSourceImpl(private val database: DailyPulseDatabase) :
    SourcesLocalDataSource {

    override fun getAllSources(): List<SourceDto> {
        return database.dailyPulseDatabaseQueries.selectAllSources(::mapToSourceDto).executeAsList()
    }

    override fun insertSources(sources: List<SourceDto>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { source ->
                database.dailyPulseDatabaseQueries.insertSource(
                    source.name.orEmpty(),
                    source.description,
                    source.language.orEmpty(),
                    source.country.orEmpty()
                )
            }
        }
    }

    override fun clearSources() {
        database.dailyPulseDatabaseQueries.removeAllSources()
    }

    private fun mapToSourceDto(
        name: String,
        description: String?,
        language: String,
        country: String?
    ) =
        SourceDto(name, description, language, country)
}