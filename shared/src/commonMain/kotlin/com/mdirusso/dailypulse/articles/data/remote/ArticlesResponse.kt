package com.mdirusso.dailypulse.articles.data.remote

import com.mdirusso.dailypulse.articles.data.dto.ArticleDto
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    val status: String?,
    val totalResults: Int?,
    val articles: List<ArticleDto>?
)