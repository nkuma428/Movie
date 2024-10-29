package com.app.movie.data.repository

import com.app.movie.data.model.QuoteResponse
import com.app.movie.data.remote.ApiService
import com.app.movie.domain.repository.GetQuoteRepository

class GetQuoteRepositoryImpl(
    private val apiService: ApiService
) : GetQuoteRepository {

    override suspend fun getQuoteByMovieId(movieId: String): Result<QuoteResponse> {
        return try {
            val response = apiService.getQuoteByMovieId()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
