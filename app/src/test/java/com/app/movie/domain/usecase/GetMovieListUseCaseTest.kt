package com.app.movie.domain.usecase
import com.app.movie.data.model.MovieResponse
import com.app.movie.domain.repository.GetMovieListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runBlockingTest
import io.mockk.MockKVerificationScope
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK

class GetMovieListUseCaseTest {

    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @RelaxedMockK
    private lateinit var repository: GetMovieListRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getMovieListUseCase = GetMovieListUseCase(repository)
    }

    @Test
    fun `should return movie list when repository call is successful`() = runBlockingTest {
        // Given
        val expectedMovies = Result.success(MovieResponse(/*...*/))
        coEvery { repository.getMovieList() } returns expectedMovies

        // When
        val result = getMovieListUseCase()

        // Then
        assertEquals(expectedMovies, result)
    }

    @Test
    fun `should return error when repository call fails`() = runBlockingTest {
        // Given
        val expectedError = Result.failure<MovieResponse>(Exception("Error message"))
        coEvery { repository.getMovieList() } returns expectedError

        // When
        val result = getMovieListUseCase()

        // Then
        assertEquals(expectedError, result)
    }
}