package com.dapascript.movieleak.domain.usecase

import com.dapascript.movieleak.data.repository.MovieRepository
import com.dapascript.movieleak.domain.model.MovieVideos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieVideosUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<Result<MovieVideos>> {
        return movieRepository.getMovieVideos(movieId)
    }
}
