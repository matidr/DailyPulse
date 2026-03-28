package com.mdirusso.dailypulse.sources.data.dto

import kotlinx.serialization.Serializable
import mdirusso.dailypulse.db.Source

@Serializable
data class SourceDto(
    val name: String?,
    val description: String?,
    val language: String?,
    val country: String?
) {
    fun toDomain() =
        Source(name.orEmpty(), description.orEmpty(), language.orEmpty(), country.orEmpty())
}
