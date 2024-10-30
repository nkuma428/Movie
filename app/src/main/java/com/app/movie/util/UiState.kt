package com.app.movie.util

/**
 * Represents the UI state with three possible states: Loading, Success, and Error.
 */
sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}