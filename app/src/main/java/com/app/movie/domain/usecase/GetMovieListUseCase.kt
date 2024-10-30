package com.app.movie.domain.usecase

import com.app.movie.data.model.MovieResponse
import com.app.movie.domain.repository.GetMovieListRepository
import javax.inject.Inject

/**
 * Use case for fetching the list of movies.
 *
 * @property repository The repository to fetch the movie list from.
 */
class GetMovieListUseCase @Inject constructor(private val repository: GetMovieListRepository) {

    /**
     * Invokes the use case to fetch the list of movies.
     *
     * @return A Result containing the MovieResponse.
     */
    suspend operator fun invoke(): Result<MovieResponse> {
        return repository.getMovieList()
    }
}