package com.dapascript.movieleak.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

	@param:Json(name = "results")
	val results: List<ResultsItem>,
)

@JsonClass(generateAdapter = true)
data class ResultsItem(

	@param:Json(name = "overview")
	val overview: String?,

	@param:Json(name = "original_title")
	val originalTitle: String?,

	@param:Json(name = "video")
	val video: Boolean?,

	@param:Json(name = "title")
	val title: String,

	@param:Json(name = "genre_ids")
	val genreIds: List<Int>?,

	@param:Json(name = "poster_path")
	val posterPath: String?,

	@param:Json(name = "backdrop_path")
	val backdropPath: String?,

	@param:Json(name = "release_date")
	val releaseDate: String?,

	@param:Json(name = "popularity")
	val popularity: Double?,

	@param:Json(name = "vote_average")
	val voteAverage: Double?,

	@param:Json(name = "id")
	val id: Int,

	@param:Json(name = "adult")
	val adult: Boolean?,

	@param:Json(name = "vote_count")
	val voteCount: Int?
)