package com.app.movie.data.repository

import com.app.movie.data.model.MovieResponse
import com.app.movie.data.remote.ApiService
import com.app.movie.domain.repository.GetMovieListRepository

class GetMovieListRepositoryImpl(
    private val apiService: ApiService
) : GetMovieListRepository {

    override suspend fun getMovieList(): Result<MovieResponse> {
        return try {
            val response = apiService.getMoviesList()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
