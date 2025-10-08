package com.dapascript.movieleak.data.mapper

import com.dapascript.movieleak.data.model.CastItem
import com.dapascript.movieleak.data.model.MovieCreditsResponse
import com.dapascript.movieleak.data.model.MovieDetailResponse
import com.dapascript.movieleak.data.model.MovieVideosResponse
import com.dapascript.movieleak.data.model.ResultsItem
import com.dapascript.movieleak.data.model.VideoResponse
import com.dapascript.movieleak.domain.model.Cast
import com.dapascript.movieleak.domain.model.Movie
import com.dapascript.movieleak.domain.model.MovieCredits
import com.dapascript.movieleak.domain.model.MovieDetail
import com.dapascript.movieleak.domain.model.MovieVideos
import com.dapascript.movieleak.domain.model.Video

fun ResultsItem.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate ?: "",
        voteAverage = this.voteAverage ?: 0.0
    )
}

fun MovieDetailResponse.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath ?: "",
        backdropPath = this.backdropPath ?: "",
        releaseDate = this.releaseDate ?: "",
        voteAverage = this.voteAverage ?: 0.0,
        overview = this.overview ?: "",
        genres = this.genres?.map { it.name } ?: emptyList(),
        runtime = this.runtime ?: 0
    )
}

fun List<ResultsItem>.toMovieList(): List<Movie> {
    return this.map { it.toMovie() }
}

fun CastItem.toCast(): Cast {
    return Cast(
        id = this.id,
        name = this.name,
        character = this.character,
        profilePath = this.profilePath
    )
}

fun MovieCreditsResponse.toMovieCredits(): MovieCredits {
    return MovieCredits(
        id = this.id,
        cast = this.cast.map { it.toCast() }
    )
}

fun VideoResponse.toVideo(): Video {
    return Video(
        id = this.id ?: "",
        key = this.key ?: "",
        name = this.name ?: "",
        site = this.site ?: "",
        type = this.type ?: "",
        official = this.official ?: false
    )
}

fun MovieVideosResponse.toMovieVideos(): MovieVideos {
    return MovieVideos(
        id = this.id ?: 0,
        results = this.results?.map { it.toVideo() } ?: emptyList()
    )
}
