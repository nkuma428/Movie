package com.app.movie.uil

import com.app.movie.data.model.Character
import com.app.movie.data.model.Movie
import com.app.movie.data.model.Quote
import com.app.movie.util.MovieUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieUtilsTest {

    @Test
    fun `updateMovieQuotes should update movies with their corresponding quotes`() {
        // Given
        val movieList = listOf(
            Movie(id = "1", quoteList = listOf()),
            Movie(id = "2", quoteList = listOf())
        )

        val quoteList = listOf(
            Quote(id = "q1", movie = "1", character = "c1", dialog = "Quote 1"),
            Quote(id = "q2", movie = "1", character = "c2", dialog = "Quote 2"),
            Quote(id = "q3", movie = "2", character = "c3", dialog = "Quote 3")
        )

        // When
        val updatedMovies = MovieUtils.updateMovieQuotes(movieList, quoteList)

        // Then
        assertEquals(2, updatedMovies.size)
        assertEquals(2, updatedMovies[0].quoteList.size)
        assertEquals(1, updatedMovies[1].quoteList.size)
    }

    @Test
    fun `updateMovieCharacters should update movies with their corresponding characters`() {
        // Given
        val movieList = listOf(
            Movie(id = "1", quoteList = listOf(Quote(id = "q1", movie = "1", character = "c1", dialog = "Quote 1"))),
            Movie(id = "2", quoteList = listOf(Quote(id = "q3", movie = "2", character = "c3", dialog = "Quote 3")))
        )

        val characterList = listOf(
            Character(id = "c1", name = "Character 1"),
            Character(id = "c2", name = "Character 2"),
            Character(id = "c3", name = "Character 3")
        )

        // When
        val updatedMovies = MovieUtils.updateMovieCharacters(movieList, characterList)

        // Then
        assertEquals(2, updatedMovies.size)
        assertEquals(1, updatedMovies[0].characterList.size)
        assertEquals("Character 1", updatedMovies[0].characterList[0].name)
        assertEquals(1, updatedMovies[1].characterList.size)
        assertEquals("Character 3", updatedMovies[1].characterList[0].name)
    }
}