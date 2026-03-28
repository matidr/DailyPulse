package com.mdirusso.dailypulse.sources.data.dto

import com.mdirusso.dailypulse.sources.domain.models.Source
import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    val name: String?,
    val description: String?,
    val language: String?,
    val country: String?
) {
    fun toDomain() =
        Source(name.orEmpty(), description, language.orEmpty(), country.orEmpty())
}
