package com.dapascript.movieleak.domain.usecase

import com.dapascript.movieleak.domain.model.Movie
import com.dapascript.movieleak.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(query: String): Flow<Result<List<Movie>>> {
        return movieRepository.searchMovie(query)
    }
}

