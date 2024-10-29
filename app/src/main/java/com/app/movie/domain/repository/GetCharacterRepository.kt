package com.app.movie.domain.repository

import com.app.movie.data.model.CharacterResponse

interface GetCharacterRepository {
    suspend fun getCharacter(movieId: String): Result<CharacterResponse>
}
