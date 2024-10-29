package com.app.movie.util

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
    }
}