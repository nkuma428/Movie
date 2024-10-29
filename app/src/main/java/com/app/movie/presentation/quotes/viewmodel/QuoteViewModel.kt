package com.app.movie.presentation.quotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movie.data.model.Quote
import com.app.movie.domain.usecase.GetQuoteUseCase
import com.app.movie.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuoteUseCase
) : ViewModel() {

    private val _quoteResponse = MutableStateFlow<UiState<List<Quote>>>(UiState.Loading)
    val quoteResponse: StateFlow<UiState<List<Quote>>> get() = _quoteResponse

    fun getCharactersByMovieId(movieId: String) {
        viewModelScope.launch {
            val result = getQuoteUseCase(movieId)
            // doc: write code for handling the result
            _quoteResponse.value = if (result.isSuccess) {
                // Handle the successful result
                UiState.Success(result.getOrNull()?.quoteList ?: emptyList())
            } else {
                // Handle the error
                UiState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
}