package com.dapascript.movieleak.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(

	@param:Json(name = "imdb_id")
	val imdbId: String?,

	@param:Json(name = "video")
	val video: Boolean?,

	@param:Json(name = "title")
	val title: String,

	@param:Json(name = "backdrop_path")
	val backdropPath: String?,

	@param:Json(name = "revenue")
	val revenue: Int?,

	@param:Json(name = "genres")
	val genres: List<GenresItem>?,

	@param:Json(name = "popularity")
	val popularity: Double?,

	@param:Json(name = "id")
	val id: Int,

	@param:Json(name = "vote_count")
	val voteCount: Int?,

	@param:Json(name = "budget")
	val budget: Int?,

	@param:Json(name = "overview")
	val overview: String?,

	@param:Json(name = "original_title")
	val originalTitle: String?,

	@param:Json(name = "runtime")
	val runtime: Int?,

	@param:Json(name = "poster_path")
	val posterPath: String?,

	@param:Json(name = "release_date")
	val releaseDate: String?,

	@param:Json(name = "vote_average")
	val voteAverage: Double?,

	@param:Json(name = "tagline")
	val tagline: String?,

	@param:Json(name = "adult")
	val adult: Boolean?,

	@param:Json(name = "homepage")
	val homepage: String?,

	@param:Json(name = "status")
	val status: String?
)

@JsonClass(generateAdapter = true)
data class GenresItem(

	@param:Json(name = "name")
	val name: String,

	@param:Json(name = "id")
	val id: Int
)