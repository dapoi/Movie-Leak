package com.dapascript.movieleak.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val overview: String,
    val genres: List<String>,
    val runtime: Int,
)

