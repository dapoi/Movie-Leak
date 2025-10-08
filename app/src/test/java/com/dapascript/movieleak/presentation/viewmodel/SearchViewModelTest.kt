package com.dapascript.movieleak.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dapascript.movieleak.domain.model.Movie
import com.dapascript.movieleak.domain.usecase.GetPopularMoviesUseCase
import com.dapascript.movieleak.domain.usecase.SearchMovieUseCase
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
class SearchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var searchMovieUseCase: SearchMovieUseCase

    @Mock
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        searchViewModel = SearchViewModel(searchMovieUseCase, getPopularMoviesUseCase)
    }

    @Test
    fun `getPopularMovies should return success`() = runTest {
        val dummyMovies = listOf(Movie(1, "title", "poster", "date", 1.0))
        val flow = flowOf(Result.success(dummyMovies))
        whenever(getPopularMoviesUseCase()).thenReturn(flow)

        searchViewModel.getPopularMovies()
        val result = searchViewModel.searchState.getOrAwaitValue()

        assertNotNull(result)
        assertEquals(UiState.Success(dummyMovies), result)
    }

    @Test
    fun `getPopularMovies should return error`() = runTest {
        val errorMessage = "Error"
        val flow = flow<Result<List<Movie>>> {
            emit(Result.failure(Throwable(errorMessage)))
        }
        whenever(getPopularMoviesUseCase()).thenReturn(flow)

        searchViewModel.getPopularMovies()
        val result = searchViewModel.searchState.getOrAwaitValue()

        assertNotNull(result)
        assertEquals(UiState.Error(errorMessage), result)
    }

    @Test
    fun `searchMovie should return success`() = runTest {
        val dummyMovies = listOf(Movie(1, "title", "poster", "date", 1.0))
        val query = "test"
        val flow = flowOf(Result.success(dummyMovies))
        whenever(searchMovieUseCase(query)).thenReturn(flow)

        searchViewModel.searchMovie(query)
        val result = searchViewModel.searchState.getOrAwaitValue()

        assertNotNull(result)
        assertEquals(UiState.Success(dummyMovies), result)
        verify(searchMovieUseCase).invoke(query)
    }

    @Test
    fun `searchMovie should return error`() = runTest {
        val errorMessage = "Error"
        val query = "test"
        val flow = flow<Result<List<Movie>>> {
            emit(Result.failure(Throwable(errorMessage)))
        }
        whenever(searchMovieUseCase(query)).thenReturn(flow)

        searchViewModel.searchMovie(query)
        val result = searchViewModel.searchState.getOrAwaitValue()

        assertNotNull(result)
        assertEquals(UiState.Error(errorMessage), result)
        verify(searchMovieUseCase).invoke(query)
    }
}
