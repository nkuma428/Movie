package com.app.movie.domain.repository

import com.app.movie.data.model.MovieResponse

/**
 * Repository interface for fetching the list of movies.
 */
interface GetMovieListRepository {

    /**
     * Fetches the list of movies.
     *
     * @return A Result containing the MovieResponse.
     */
    suspend fun getMovieList(): Result<MovieResponse>
}
