package com.dapascript.movieleak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapascript.movieleak.domain.model.MovieCredits
import com.dapascript.movieleak.domain.model.MovieDetail
import com.dapascript.movieleak.domain.usecase.GetMovieCreditsUseCase
import com.dapascript.movieleak.domain.usecase.GetMovieDetailUseCase
import com.dapascript.movieleak.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase
) : ViewModel() {

    private val _detailState = MutableStateFlow<UiState<MovieDetail>>(UiState.Loading)
    val detailState: StateFlow<UiState<MovieDetail>> = _detailState

    private val _creditsState = MutableStateFlow<UiState<MovieCredits>>(UiState.Loading)
    val creditsState: StateFlow<UiState<MovieCredits>> = _creditsState

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase(movieId).catch {
                _detailState.value = UiState.Error(it.message.toString())
            }.collect {
                it.onSuccess { movieDetail ->
                    _detailState.value = UiState.Success(movieDetail)
                }.onFailure { throwable ->
                    _detailState.value = UiState.Error(throwable.message.toString())
                }
            }
        }
    }

    fun getMovieCredits(movieId: Int) {
        viewModelScope.launch {
            getMovieCreditsUseCase(movieId).catch {
                _creditsState.value = UiState.Error(it.message.toString())
            }.collect {
                it.onSuccess { movieCredits ->
                    _creditsState.value = UiState.Success(movieCredits)
                }.onFailure { throwable ->
                    _creditsState.value = UiState.Error(throwable.message.toString())
                }
            }
        }
    }
}
