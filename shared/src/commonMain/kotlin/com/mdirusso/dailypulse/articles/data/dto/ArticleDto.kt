package com.mdirusso.dailypulse.articles.data.dto

import com.mdirusso.dailypulse.articles.domain.models.Article
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.abs

@Serializable
data class ArticleDto(
    val title: String?,
    val description: String?,
    @SerialName("publishedAt") val date: String?,
    @SerialName("urlToImage") val imageUrl: String?
) {
    fun toDomain() = Article(
        title = title.orEmpty(),
        description = description ?: "Click to find out more",
        date = getDaysAgoString(date.orEmpty()),
        imageUrl = imageUrl
            ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
    )

    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.Companion.currentSystemDefault())
        val days = today.daysUntil(
            Instant.Companion.parse(date)
                .toLocalDateTime(TimeZone.Companion.currentSystemDefault()).date
        )
        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}