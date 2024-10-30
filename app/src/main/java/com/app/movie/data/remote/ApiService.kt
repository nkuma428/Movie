package com.app.movie.data.remote

import com.app.movie.data.model.CharacterResponse
import com.app.movie.data.model.MovieResponse
import com.app.movie.data.model.QuoteResponse
import com.app.movie.util.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API service interface for Retrofit to handle network requests.
 */
interface ApiService {

    /**
     * Fetches the list of movies.
     *
     * @return A [MovieResponse] containing the list of movies.
     */
    @GET("movie")
    suspend fun getMoviesList(): MovieResponse

    /**
     * Fetches the character by ID.
     *
     * @return A [CharacterResponse] containing the character details.
     */
    @GET("character")
    suspend fun getCharacterById(): CharacterResponse

    /**
     * Fetches the quotes by movie ID.
     *
     * @return A [QuoteResponse] containing the list of quotes.
     */
    @GET("quote")
    suspend fun getQuoteByMovieId(): QuoteResponse

    /**
     * Fetches the list of movies with pagination.
     *
     * @param limit The number of movies to fetch.
     * @param offset The offset for pagination.
     * @return A [Call] object for the [MovieResponse].
     */
    @GET("movie")
    suspend fun getMovies(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<MovieResponse>

    /**
     * Companion object for creating an instance of [ApiService] using Retrofit.
     */
    companion object {
        /**
         * Creates an instance of [ApiService].
         *
         * @return An instance of [ApiService].
         */
        fun create(): ApiService {

            // Create a logging interceptor to log the API calls
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            // Create an OkHttpClient and add the logging interceptor
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}