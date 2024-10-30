package com.app.movie.data.repository

import com.app.movie.data.model.CharacterResponse
import com.app.movie.data.model.MovieResponse
import com.app.movie.data.model.QuoteResponse
import com.app.movie.data.remote.ApiService
import com.app.movie.domain.repository.GetMovieListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

//Unit test class for GetMovieListRepositoryImpl.

@ExperimentalCoroutinesApi
class GetMovieListRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: GetMovieListRepository

    //Sets up the test environment before each test.
    @Before
    fun setUp() {
        apiService = mockk()
        repository = GetMovieListRepositoryImpl(apiService)
    }

    //Test to verify that `getMovieList` returns a success result when API calls are successful.
    @Test
    fun `getMovieList should return success result when API calls are successful`() = runTest {
        // Given
        val movieResponse = MovieResponse(/*...*/)
        val characterResponse = CharacterResponse(/*...*/)
        val quoteResponse = QuoteResponse(/*...*/)

        coEvery { apiService.getMoviesList() } returns movieResponse
        coEvery { apiService.getCharacterById() } returns characterResponse
        coEvery { apiService.getQuoteByMovieId() } returns quoteResponse

        // When
        val result = repository.getMovieList()

        // Then
        assert(result.isSuccess)
        assertEquals(movieResponse, result.getOrNull())
    }

    //Test to verify that getMovieList returns a failure result when an API call fails.
    @Test
    fun `getMovieList should return failure result when API call fails`() = runTest {
        // Given
        val exceptionMessage = "Error message"
        val exception = Exception(exceptionMessage)
        coEvery { apiService.getMoviesList() } throws exception
        coEvery { apiService.getCharacterById() } throws exception
        coEvery { apiService.getQuoteByMovieId() } throws exception

        // When
        val result = repository.getMovieList()

        // Then
        assert(result.isFailure)
        assertEquals(exceptionMessage, result.exceptionOrNull()?.message)
    }
}