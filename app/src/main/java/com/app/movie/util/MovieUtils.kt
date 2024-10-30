package com.app.movie.util

import android.content.SharedPreferences
import com.app.movie.data.model.Character
import com.app.movie.data.model.Movie
import com.app.movie.data.model.Quote

/**
 * Utility class for updating movie-related data.
 */
class MovieUtils {
    companion object {
        /**
         * Updates the list of movies with their corresponding quotes.
         *
         * @param movieList The list of movies to update.
         * @param quoteList The list of quotes to associate with the movies.
         * @return A list of movies with their quotes updated.
         */
        fun updateMovieQuotes(movieList: List<Movie>, quoteList: List<Quote>): List<Movie> {
            return movieList.map { movie ->
                movie.apply {
                    this.quoteList = ArrayList(quoteList.filter { it.movie == id })
                }
            }
        }

        /**
         * Updates the list of movies with their corresponding characters.
         *
         * @param movieList The list of movies to update.
         * @param characterList The list of characters to associate with the movies.
         * @return A list of movies with their characters updated.
         */
        fun updateMovieCharacters(movieList: List<Movie>, characterList: List<Character>): List<Movie> {
            return movieList.map { movie ->
                val characterIds = movie.quoteList.mapNotNull { it.character }
                movie.apply {
                    this.characterList = ArrayList(characterList.filter { it.id in characterIds })
                }
            }
        }
        fun loadFavorites(sharedPreferences: SharedPreferences): List<String> {
            return sharedPreferences.getStringSet("favorite_movies", emptySet())?.toList() ?: emptyList()
        }

        fun toggleFavorite(sharedPreferences: SharedPreferences, movieId: String, onUpdated: (List<String>) -> Unit) {
            val currentFavorites = sharedPreferences.getStringSet("favorite_movies", emptySet())?.toMutableSet() ?: mutableSetOf()

            if (currentFavorites.contains(movieId)) {
                currentFavorites.remove(movieId) // Remove from favorites
            } else {
                currentFavorites.add(movieId) // Add to favorites
            }

            // Save updated favorites to SharedPreferences
            sharedPreferences.edit().putStringSet("favorite_movies", currentFavorites).apply()

            onUpdated(currentFavorites.toList()) // Callback to update the state
        }
    }
}