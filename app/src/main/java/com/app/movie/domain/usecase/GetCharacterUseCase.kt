package com.app.movie.domain.usecase

import com.app.movie.data.model.CharacterResponse
import com.app.movie.domain.repository.GetCharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: GetCharacterRepository) {
    suspend operator fun invoke(movieId: String): Result<CharacterResponse> {
        return repository.getCharacter(movieId)
    }
}