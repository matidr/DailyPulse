package com.mdirusso.dailypulse.sources.presentation

import com.mdirusso.dailypulse.sources.domain.models.Source

data class SourceUiModel(
    val name: String,
    val description: String?,
    val locale: String
)

fun Source.toUiModel() = SourceUiModel(name, description, "$country - $language")
