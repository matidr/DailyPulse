package com.mdirusso.dailypulse.sources.domain

import mdirusso.dailypulse.db.Source

interface SourcesRepository {

    suspend fun getSources(forceFetch: Boolean): List<Source>
}