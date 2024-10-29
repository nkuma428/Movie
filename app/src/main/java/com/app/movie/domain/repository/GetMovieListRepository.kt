package com.app.movie.domain.repository

import com.app.movie.data.model.MovieResponse

interface GetMovieListRepository {
    suspend fun getMovieList(): Result<MovieResponse>
}
