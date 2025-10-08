package com.dapascript.movieleak.data.repository

import com.dapascript.movieleak.data.api.ApiService
import com.dapascript.movieleak.data.mapper.toMovieCredits
import com.dapascript.movieleak.data.mapper.toMovieDetail
import com.dapascript.movieleak.data.mapper.toMovieList
import com.dapascript.movieleak.domain.model.Movie
import com.dapascript.movieleak.domain.model.MovieCredits
import com.dapascript.movieleak.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    override suspend fun searchMovie(query: String): Flow<Result<List<Movie>>> {
        return flow {
            val response = apiService.searchMovie(query)
            val movies = response.results.toMovieList()
            emit(Result.success(movies))
        }.catch { e ->
            emit(Result.failure(e))
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<Result<MovieDetail>> {
        return flow {
            val response = apiService.getMovieDetail(movieId)
            val movieDetail = response.toMovieDetail()
            emit(Result.success(movieDetail))
        }.catch { e ->
            emit(Result.failure(e))
        }
    }

    override suspend fun getPopularMovies(): Flow<Result<List<Movie>>> {
        return flow {
            val response = apiService.getPopularMovies()
            val movies = response.results.toMovieList()
            emit(Result.success(movies))
        }.catch { e ->
            emit(Result.failure(e))
        }
    }

    override suspend fun getMovieCredits(movieId: Int): Flow<Result<MovieCredits>> {
        return flow {
            val response = apiService.getMovieCredits(movieId)
            val movieCredits = response.toMovieCredits()
            emit(Result.success(movieCredits))
        }.catch { e ->
            emit(Result.failure(e))
        }
    }
}