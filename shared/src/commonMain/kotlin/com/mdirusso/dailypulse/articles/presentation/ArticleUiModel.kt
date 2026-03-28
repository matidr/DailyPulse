package com.mdirusso.dailypulse.articles.presentation

import com.mdirusso.dailypulse.articles.domain.models.Article

data class ArticleUiModel(
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String
)

fun Article.toUiModel() = ArticleUiModel(title, description, date, imageUrl)
