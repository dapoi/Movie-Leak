package com.dapascript.movieleak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapascript.movieleak.domain.model.Movie
import com.dapascript.movieleak.domain.usecase.GetPopularMoviesUseCase
import com.dapascript.movieleak.domain.usecase.SearchMovieUseCase
import com.dapascript.movieleak.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val searchState: StateFlow<UiState<List<Movie>>> = _searchState

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase().catch {
                _searchState.value = UiState.Error(it.message.toString())
            }.collect {
                it.onSuccess { movies ->
                    _searchState.value = UiState.Success(movies)
                }.onFailure { throwable ->
                    _searchState.value = UiState.Error(throwable.message.toString())
                }
            }
        }
    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            searchMovieUseCase(query).catch {
                _searchState.value = UiState.Error(it.message.toString())
            }.collect {
                it.onSuccess { movies ->
                    _searchState.value = UiState.Success(movies)
                }.onFailure { throwable ->
                    _searchState.value = UiState.Error(throwable.message.toString())
                }
            }
        }
    }
}
