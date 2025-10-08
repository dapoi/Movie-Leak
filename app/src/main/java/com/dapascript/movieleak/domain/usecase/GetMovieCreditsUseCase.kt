package com.dapascript.movieleak.domain.usecase

import com.dapascript.movieleak.domain.model.MovieCredits
import com.dapascript.movieleak.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<Result<MovieCredits>> {
        return movieRepository.getMovieCredits(movieId)
    }
}
