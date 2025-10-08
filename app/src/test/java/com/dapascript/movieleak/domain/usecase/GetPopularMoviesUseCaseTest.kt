package com.dapascript.movieleak.domain.usecase

import com.dapascript.movieleak.data.repository.MovieRepository
import com.dapascript.movieleak.domain.model.Movie
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `invoke should return popular movies from repository`() = runTest {
        // Given
        val dummyMovies = listOf(Movie(1, "title", "poster", "date", 1.0))
        val expectedFlow = flowOf(Result.success(dummyMovies))
        whenever(movieRepository.getPopularMovies()).thenReturn(expectedFlow)

        // When
        val result = getPopularMoviesUseCase.invoke()

        // Then
        assertEquals(expectedFlow.first(), result.first())
    }
}

