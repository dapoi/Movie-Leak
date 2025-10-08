package com.dapascript.movieleak.domain.usecase

import com.dapascript.movieleak.data.repository.MovieRepository
import com.dapascript.movieleak.domain.model.Cast
import com.dapascript.movieleak.domain.model.MovieCredits
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
class GetMovieCreditsUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var getMovieCreditsUseCase: GetMovieCreditsUseCase

    @Before
    fun setUp() {
        getMovieCreditsUseCase = GetMovieCreditsUseCase(movieRepository)
    }

    @Test
    fun `invoke should return movie credits from repository`() = runTest {
        // Given
        val movieId = 1
        val dummyCredits = MovieCredits(1, listOf(Cast(1, "name", "character", "profile")))
        val expectedFlow = flowOf(Result.success(dummyCredits))
        whenever(movieRepository.getMovieCredits(movieId)).thenReturn(expectedFlow)

        // When
        val result = getMovieCreditsUseCase.invoke(movieId)

        // Then
        assertEquals(expectedFlow.first(), result.first())
    }
}

