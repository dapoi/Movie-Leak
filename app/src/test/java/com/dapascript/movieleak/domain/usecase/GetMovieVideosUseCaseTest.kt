package com.dapascript.movieleak.domain.usecase

import com.dapascript.movieleak.data.repository.MovieRepository
import com.dapascript.movieleak.domain.model.MovieVideos
import com.dapascript.movieleak.domain.model.Video
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMovieVideosUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var getMovieVideosUseCase: GetMovieVideosUseCase

    @Before
    fun setUp() {
        getMovieVideosUseCase = GetMovieVideosUseCase(movieRepository)
    }

    @Test
    fun `invoke should return success with movie videos`() = runTest {
        // Given
        val movieId = 1
        val dummyVideo = Video(
            id = "video1",
            key = "dQw4w9WgXcQ",
            name = "Official Trailer",
            site = "YouTube",
            type = "Trailer",
            official = true
        )
        val dummyMovieVideos = MovieVideos(
            id = movieId,
            results = listOf(dummyVideo)
        )
        whenever(movieRepository.getMovieVideos(movieId)).thenReturn(flowOf(Result.success(dummyMovieVideos)))

        // When
        val result = getMovieVideosUseCase(movieId).first()

        // Then
        verify(movieRepository).getMovieVideos(movieId)
        assertTrue(result.isSuccess)
        assertEquals(dummyMovieVideos, result.getOrNull())
    }

    @Test
    fun `invoke should return failure when repository fails`() = runTest {
        // Given
        val movieId = 1
        val exception = RuntimeException("Network error")
        whenever(movieRepository.getMovieVideos(movieId)).thenReturn(flowOf(Result.failure(exception)))

        // When
        val result = getMovieVideosUseCase(movieId).first()

        // Then
        verify(movieRepository).getMovieVideos(movieId)
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `invoke should return empty list when no videos available`() = runTest {
        // Given
        val movieId = 1
        val emptyMovieVideos = MovieVideos(
            id = movieId,
            results = emptyList()
        )
        whenever(movieRepository.getMovieVideos(movieId)).thenReturn(flowOf(Result.success(emptyMovieVideos)))

        // When
        val result = getMovieVideosUseCase(movieId).first()

        // Then
        verify(movieRepository).getMovieVideos(movieId)
        assertTrue(result.isSuccess)
        assertEquals(emptyMovieVideos, result.getOrNull())
        assertTrue(result.getOrNull()?.results?.isEmpty() == true)
    }
}
