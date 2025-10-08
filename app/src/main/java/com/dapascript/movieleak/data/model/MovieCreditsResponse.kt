package com.dapascript.movieleak.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCreditsResponse(
    @param:Json(name = "id")
    val id: Int,

    @param:Json(name = "cast")
    val cast: List<CastItem>
)

@JsonClass(generateAdapter = true)
data class CastItem(
    @param:Json(name = "id")
    val id: Int,

    @param:Json(name = "name")
    val name: String,

    @param:Json(name = "profile_path")
    val profilePath: String?,

    @param:Json(name = "character")
    val character: String?
)