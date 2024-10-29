package com.app.movie.domain.repository

import com.app.movie.data.model.QuoteResponse

interface GetQuoteRepository {
    suspend fun getQuoteByMovieId(movieId: String): Result<QuoteResponse>
}
