package com.dapascript.movieleak.domain.model

data class MovieVideos(
    val id: Int,
    val results: List<Video>
)

data class Video(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String,
    val official: Boolean
)
