package com.dapascript.movieleak.data.repository

import com.dapascript.movieleak.domain.model.Movie
import com.dapascript.movieleak.domain.model.MovieCredits
import com.dapascript.movieleak.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun searchMovie(query: String): Flow<Result<List<Movie>>>

    suspend fun getMovieDetail(movieId: Int): Flow<Result<MovieDetail>>

    suspend fun getPopularMovies(): Flow<Result<List<Movie>>>

    suspend fun getMovieCredits(movieId: Int): Flow<Result<MovieCredits>>
}