package com.mdirusso.dailypulse.sources.data.remote

import com.mdirusso.dailypulse.sources.data.dto.SourceDto
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    val status: String?,
    val sources: List<SourceDto>?
)