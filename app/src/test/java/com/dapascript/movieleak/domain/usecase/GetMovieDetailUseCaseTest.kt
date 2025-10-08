package com.dapascript.movieleak.domain.usecase

import com.dapascript.movieleak.data.repository.MovieRepository
import com.dapascript.movieleak.domain.model.MovieDetail
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
class GetMovieDetailUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Before
    fun setUp() {
        getMovieDetailUseCase = GetMovieDetailUseCase(movieRepository)
    }

    @Test
    fun `invoke should return movie detail from repository`() = runTest {
        // Given
        val movieId = 1
        val dummyDetail = MovieDetail(1, "title", "poster", "backdrop", "date", 1.0, "overview", emptyList(), 120)
        val expectedFlow = flowOf(Result.success(dummyDetail))
        whenever(movieRepository.getMovieDetail(movieId)).thenReturn(expectedFlow)

        // When
        val result = getMovieDetailUseCase.invoke(movieId)

        // Then
        assertEquals(expectedFlow.first(), result.first())
    }
}

