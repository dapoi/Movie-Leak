package com.dapascript.movieleak.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapascript.movieleak.domain.model.MovieDetail
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
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _detailState = MutableStateFlow<UiState<MovieDetail>>(UiState.Loading)
    val detailState: StateFlow<UiState<MovieDetail>> = _detailState

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
}

