package com.legalstream.app.model

data class Movie(
    val identifier: String,
    val title: String,
    val description: String = "",
    val thumbnailUrl: String = "",
    val videoUrl: String = ""
)
