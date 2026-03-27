package com.mdirusso.dailypulse.articles.domain.models

data class Article(
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String
)