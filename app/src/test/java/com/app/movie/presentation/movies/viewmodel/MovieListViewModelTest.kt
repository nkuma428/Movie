package com.app.movie.presentation.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.movie.data.model.Movie
import com.app.movie.data.model.MovieResponse
import com.app.movie.domain.usecase.GetMovieListUseCase
import com.app.movie.util.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

//Unit test class for MovieListViewModel
@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    //Rule to allow LiveData to execute synchronously.

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieListViewModel
    private lateinit var getMovieListUseCase: GetMovieListUseCase

    private val testDispatcher = UnconfinedTestDispatcher()

    //Sets up the test environment before each test.
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getMovieListUseCase = mockk()
        viewModel = MovieListViewModel(getMovieListUseCase)
    }

    //Test to verify that `loadMovies` emits a success state when the use case returns data.
    @Test
    fun `loadMovies should emit success state when use case returns data`() = runTest {
        // Given
        val movies = listOf(Movie(/*...*/))
        val expectedResult = Result.success(MovieResponse(movies))
        coEvery { getMovieListUseCase.invoke() } returns expectedResult

        // When
        viewModel.loadMovies()

        // Then
        val state = viewModel.movieResponse.value
        assert(state is UiState.Success)
        assertEquals(movies, (state as UiState.Success).data)
    }

    //Test to verify that `loadMovies` emits an error state when the use case returns an error.
    @Test
    fun `loadMovies should emit error state when use case returns error`() = runTest {
        // Given
        val expectedError = Result.failure<MovieResponse>(Exception("Error message"))
        coEvery { getMovieListUseCase.invoke() } returns expectedError

        // When
        viewModel.loadMovies()

        // Then
        val state = viewModel.movieResponse.value
        assert(state is UiState.Error)
        assertEquals("Error message", (state as UiState.Error).message)
    }


    //Cleans up the test environment after each test.
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}