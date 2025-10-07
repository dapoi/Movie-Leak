package com.dapascript.movieleak.domain.usecase

import com.dapascript.movieleak.domain.model.MovieDetail
import com.dapascript.movieleak.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<Result<MovieDetail>> {
        return movieRepository.getMovieDetail(movieId)
    }
}

