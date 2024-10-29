package com.app.movie.presentation.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movie.data.model.Character
import com.app.movie.domain.usecase.GetCharacterUseCase
import com.app.movie.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _characterResponse = MutableStateFlow<UiState<List<Character>>>(UiState.Loading)
    val characterResponse: StateFlow<UiState<List<Character>>> get() = _characterResponse

    fun getCharactersByMovieId(movieId: String) {
        viewModelScope.launch {
            val result = getCharacterUseCase(movieId)
            // doc: write code for handling the result
            _characterResponse.value = if (result.isSuccess) {
                // Handle the successful result
                UiState.Success(result.getOrNull()?.characterList ?: emptyList())
            } else {
                // Handle the error
                UiState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
}