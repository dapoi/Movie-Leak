package com.dapascript.movieleak.data.repository

import com.dapascript.movieleak.data.api.ApiService
import com.dapascript.movieleak.data.mapper.toMovieCredits
import com.dapascript.movieleak.data.mapper.toMovieDetail
import com.dapascript.movieleak.data.mapper.toMovieList
import com.dapascript.movieleak.data.model.CastItem
import com.dapascript.movieleak.data.model.MovieCreditsResponse
import com.dapascript.movieleak.data.model.MovieDetailResponse
import com.dapascript.movieleak.data.model.MovieResponse
import com.dapascript.movieleak.data.model.ResultsItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        movieRepository = MovieRepositoryImpl(apiService)
    }

    @Test
    fun `searchMovie should return success`() = runTest {
        // Given
        val query = "test"
        val dummyResponse = MovieResponse(
            results = listOf(
                ResultsItem(
                    id = 1,
                    title = "title",
                    posterPath = "poster",
                    releaseDate = "date",
                    voteAverage = 1.0,
                    overview = "",
                    originalTitle = "",
                    video = false,
                    genreIds = emptyList(),
                    backdropPath = "",
                    popularity = 0.0,
                    adult = false,
                    voteCount = 0
                )
            )
        )
        whenever(apiService.searchMovie(query)).thenReturn(dummyResponse)

        // When
        val result = movieRepository.searchMovie(query).first()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(dummyResponse.results.toMovieList(), result.getOrNull())
    }

    @Test
    fun `searchMovie should return failure`() = runTest {
        // Given
        val query = "test"
        val exception = RuntimeException("Error")
        whenever(apiService.searchMovie(query)).thenThrow(exception)

        // When
        val result = movieRepository.searchMovie(query).first()

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `getMovieDetail should return success`() = runTest {
        // Given
        val movieId = 1
        val dummyResponse = MovieDetailResponse(
            id = 1,
            title = "title",
            posterPath = "poster",
            backdropPath = "backdrop",
            releaseDate = "date",
            voteAverage = 1.0,
            overview = "overview",
            genres = emptyList(),
            originalTitle = "original",
            runtime = 120,
            tagline = "tagline",
            homepage = "homepage",
            adult = false,
            video = false,
            popularity = 0.0,
            imdbId = "imdb",
            revenue = 0,
            voteCount = 0,
            budget = 0,
            status = "status"
        )
        whenever(apiService.getMovieDetail(movieId)).thenReturn(dummyResponse)

        // When
        val result = movieRepository.getMovieDetail(movieId).first()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(dummyResponse.toMovieDetail(), result.getOrNull())
    }

    @Test
    fun `getMovieDetail should return failure`() = runTest {
        // Given
        val movieId = 1
        val exception = RuntimeException("Error")
        whenever(apiService.getMovieDetail(movieId)).thenThrow(exception)

        // When
        val result = movieRepository.getMovieDetail(movieId).first()

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `getPopularMovies should return success`() = runTest {
        // Given
        val dummyResponse = MovieResponse(
            results = listOf(
                ResultsItem(
                    id = 1,
                    title = "title",
                    posterPath = "poster",
                    releaseDate = "date",
                    voteAverage = 1.0,
                    overview = "",
                    originalTitle = "",
                    video = false,
                    genreIds = emptyList(),
                    backdropPath = "",
                    popularity = 0.0,
                    adult = false,
                    voteCount = 0
                )
            )
        )
        whenever(apiService.getPopularMovies()).thenReturn(dummyResponse)

        // When
        val result = movieRepository.getPopularMovies().first()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(dummyResponse.results.toMovieList(), result.getOrNull())
    }

    @Test
    fun `getPopularMovies should return failure`() = runTest {
        // Given
        val exception = RuntimeException("Error")
        whenever(apiService.getPopularMovies()).thenThrow(exception)

        // When
        val result = movieRepository.getPopularMovies().first()

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `getMovieCredits should return success`() = runTest {
        // Given
        val movieId = 1
        val dummyResponse = MovieCreditsResponse(
            id = 1,
            cast = listOf(
                CastItem(
                    id = 1,
                    name = "name",
                    character = "character",
                    profilePath = "profile"
                )
            )
        )
        whenever(apiService.getMovieCredits(movieId)).thenReturn(dummyResponse)

        // When
        val result = movieRepository.getMovieCredits(movieId).first()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(dummyResponse.toMovieCredits(), result.getOrNull())
    }

    @Test
    fun `getMovieCredits should return failure`() = runTest {
        // Given
        val movieId = 1
        val exception = RuntimeException("Error")
        whenever(apiService.getMovieCredits(movieId)).thenThrow(exception)

        // When
        val result = movieRepository.getMovieCredits(movieId).first()

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
