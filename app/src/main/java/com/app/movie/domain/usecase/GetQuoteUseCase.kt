package com.app.movie.domain.usecase

import com.app.movie.data.model.QuoteResponse
import com.app.movie.domain.repository.GetQuoteRepository
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(private val repository: GetQuoteRepository) {
    suspend operator fun invoke(movieId: String): Result<QuoteResponse> {
        return repository.getQuoteByMovieId(movieId)
    }
}