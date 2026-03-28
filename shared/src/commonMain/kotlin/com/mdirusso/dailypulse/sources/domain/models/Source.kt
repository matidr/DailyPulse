package com.mdirusso.dailypulse.sources.domain.models

data class Source(
    val name: String,
    val description: String?,
    val language: String,
    val country: String,
)