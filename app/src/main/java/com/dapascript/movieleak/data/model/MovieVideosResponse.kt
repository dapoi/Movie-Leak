package com.dapascript.movieleak.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieVideosResponse(
    @param:Json(name = "id")
    val id: Int?,
    @param:Json(name = "results")
    val results: List<VideoResponse>?
)

@JsonClass(generateAdapter = true)
data class VideoResponse(
    @param:Json(name = "id")
    val id: String?,
    @param:Json(name = "key")
    val key: String?,
    @param:Json(name = "name")
    val name: String?,
    @param:Json(name = "site")
    val site: String?,
    @param:Json(name = "type")
    val type: String?,
    @param:Json(name = "official")
    val official: Boolean?
)
