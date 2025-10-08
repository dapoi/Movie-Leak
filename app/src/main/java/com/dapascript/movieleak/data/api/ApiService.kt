package com.dapascript.movieleak.data.api

import com.dapascript.movieleak.data.model.MovieCreditsResponse
import com.dapascript.movieleak.data.model.MovieDetailResponse
import com.dapascript.movieleak.data.model.MovieResponse
import com.dapascript.movieleak.data.model.MovieVideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
    ): MovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
    ): MovieCreditsResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
    ): MovieVideosResponse
}
