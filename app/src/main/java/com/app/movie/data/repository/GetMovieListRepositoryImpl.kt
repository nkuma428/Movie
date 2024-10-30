package com.app.movie.data.repository

import com.app.movie.data.model.MovieResponse
import com.app.movie.data.remote.ApiService
import com.app.movie.domain.repository.GetMovieListRepository
import com.app.movie.util.MovieUtils.Companion.updateMovieCharacters
import com.app.movie.util.MovieUtils.Companion.updateMovieQuotes
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * Fetches the list of movies, updates them with characters and quotes, and returns the result.
 *
 * @return A Result containing the MovieResponse.
 */
class GetMovieListRepositoryImpl(
    private val apiService: ApiService
) : GetMovieListRepository {

    /**
     * Fetches the list of movies, updates them with characters and quotes, and returns the result.
     *
     * @return A Result containing the MovieResponse.
     */
    override suspend fun getMovieList(): Result<MovieResponse> {
        return try {
            val movieResponse = coroutineScope {
                val movieDeferred = async { apiService.getMoviesList() }
                val characterDeferred = async { apiService.getCharacterById() }
                val quotesDeferred = async { apiService.getQuoteByMovieId() }

                val movieResponse = movieDeferred.await()
                val characterResponse = characterDeferred.await()
                val quotesResponse = quotesDeferred.await()

                if (movieResponse.movieList.isNotEmpty()) {
                    val updatedMovies = updateMovieQuotes(movieResponse.movieList, quotesResponse.quoteList)
                    movieResponse.movieList = ArrayList(updateMovieCharacters(updatedMovies, characterResponse.characterList))
                }
                movieResponse
            }
            Result.success(movieResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}