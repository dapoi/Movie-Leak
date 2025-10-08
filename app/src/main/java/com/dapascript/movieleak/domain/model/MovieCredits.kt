package com.dapascript.movieleak.domain.model

data class MovieCredits(
    val id: Int,
    val cast: List<Cast>
)

data class Cast(
    val id: Int,
    val name: String,
    val character: String?,
    val profilePath: String?
)