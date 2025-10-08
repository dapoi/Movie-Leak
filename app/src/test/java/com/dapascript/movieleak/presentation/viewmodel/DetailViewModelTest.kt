package com.dapascript.movieleak.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dapascript.movieleak.domain.model.Cast
import com.dapascript.movieleak.domain.model.MovieCredits
import com.dapascript.movieleak.domain.model.MovieDetail
import com.dapascript.movieleak.domain.usecase.GetMovieCreditsUseCase
import com.dapascript.movieleak.domain.usecase.GetMovieDetailUseCase
import com.dapascript.movieleak.presentation.utils.UiState
import com.dapascript.movieleak.utils.MainCoroutineRule
import com.dapascript.movieleak.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Mock
    private lateinit var getMovieCreditsUseCase: GetMovieCreditsUseCase

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(getMovieDetailUseCase, getMovieCreditsUseCase)
    }

    @Test
    fun `getMovieDetail should return success`() = runTest {
        val dummyDetail = MovieDetail(1, "title", "poster", "backdrop", "date", 1.0, "overview", emptyList(), 120)
        val movieId = 1
        val flow = flowOf(Result.success(dummyDetail))
        whenever(getMovieDetailUseCase(movieId)).thenReturn(flow)

        detailViewModel.getMovieDetail(movieId)
        val result = detailViewModel.detailState.getOrAwaitValue()

        assertNotNull(result)
        assertEquals(UiState.Success(dummyDetail), result)
        verify(getMovieDetailUseCase).invoke(movieId)
    }

    @Test
    fun `getMovieDetail should return error`() = runTest {
        val errorMessage = "Error"
        val movieId = 1
        val flow = flow<Result<MovieDetail>> {
            emit(Result.failure(Throwable(errorMessage)))
        }
        whenever(getMovieDetailUseCase(movieId)).thenReturn(flow)

        detailViewModel.getMovieDetail(movieId)
        val result = detailViewModel.detailState.getOrAwaitValue()

        assertNotNull(result)
        assertEquals(UiState.Error(errorMessage), result)
        verify(getMovieDetailUseCase).invoke(movieId)
    }

    @Test
    fun `getMovieCredits should return success`() = runTest {
        val dummyCredits = MovieCredits(1, listOf(Cast(1, "name", "character", "profile")))
        val movieId = 1
        val flow = flowOf(Result.success(dummyCredits))
        whenever(getMovieCreditsUseCase(movieId)).thenReturn(flow)

        detailViewModel.getMovieCredits(movieId)
        val result = detailViewModel.creditsState.getOrAwaitValue()

        assertNotNull(result)
        assertEquals(UiState.Success(dummyCredits), result)
        verify(getMovieCreditsUseCase).invoke(movieId)
    }

    @Test
    fun `getMovieCredits should return error`() = runTest {
        val errorMessage = "Error"
        val movieId = 1
        val flow = flow<Result<MovieCredits>> {
            emit(Result.failure(Throwable(errorMessage)))
        }
        whenever(getMovieCreditsUseCase(movieId)).thenReturn(flow)

        detailViewModel.getMovieCredits(movieId)
        val result = detailViewModel.creditsState.getOrAwaitValue()

        assertNotNull(result)
        assertEquals(UiState.Error(errorMessage), result)
        verify(getMovieCreditsUseCase).invoke(movieId)
    }
}
