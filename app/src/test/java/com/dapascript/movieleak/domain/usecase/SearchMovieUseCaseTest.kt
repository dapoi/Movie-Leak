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
class SearchMovieUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var searchMovieUseCase: SearchMovieUseCase

    @Before
    fun setUp() {
        searchMovieUseCase = SearchMovieUseCase(movieRepository)
    }

    @Test
    fun `invoke should return searched movies from repository`() = runTest {
        // Given
        val query = "test"
        val dummyMovies = listOf(Movie(1, "title", "poster", "date", 1.0))
        val expectedFlow = flowOf(Result.success(dummyMovies))
        whenever(movieRepository.searchMovie(query)).thenReturn(expectedFlow)

        // When
        val result = searchMovieUseCase.invoke(query)

        // Then
        assertEquals(expectedFlow.first(), result.first())
    }
}

