package com.app.movie.data.repository

import com.app.movie.data.model.CharacterResponse
import com.app.movie.data.remote.ApiService
import com.app.movie.domain.repository.GetCharacterRepository

class GetCharacterRepositoryImpl(
    private val apiService: ApiService
) : GetCharacterRepository {

    override suspend fun getCharacter(movieId: String): Result<CharacterResponse> {
        return try {
            val response = apiService.getCharacterById()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
