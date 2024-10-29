package com.app.movie.presentation.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movie.data.model.Movie
import com.app.movie.domain.usecase.GetMovieListUseCase
import com.app.movie.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {
    init {
        loadMovies()
    }

    private val _movieResponse = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val movieResponse: StateFlow<UiState<List<Movie>>> get() = _movieResponse

    private fun loadMovies() {
        viewModelScope.launch {
            val result = getMovieListUseCase()
            // doc: write code for handling the result
            _movieResponse.value = if (result.isSuccess) {
                // Handle the successful result
                UiState.Success(result.getOrNull()?.movieList ?: emptyList())
            } else {
                // Handle the error
                UiState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
}