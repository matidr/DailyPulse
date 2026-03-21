package com.mdirusso.dailypulse.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val title: String?,
    val description: String?,
    @SerialName("publishedAt") val date: String?,
    @SerialName("urlToImage") val imageUrl: String?
)