package com.app.movie.domain.usecase

import com.app.movie.data.model.MovieResponse
import com.app.movie.domain.repository.GetMovieListRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: GetMovieListRepository) {
    suspend operator fun invoke(): Result<MovieResponse> {
        return repository.getMovieList()
    }
}