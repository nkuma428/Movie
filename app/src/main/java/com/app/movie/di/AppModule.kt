package com.app.movie.di

import com.app.movie.data.remote.ApiService
import com.app.movie.data.repository.GetMovieListRepositoryImpl
import com.app.movie.domain.repository.GetMovieListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of [ApiService].
     *
     * @return An instance of [ApiService].
     */
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService.create()
    }

    /**
     * Provides a singleton instance of [GetMovieListRepository].
     *
     * @param apiService The [ApiService] instance to be used by the repository.
     * @return An instance of [GetMovieListRepository].
     */
    @Provides
    @Singleton
    fun provideGetMovieListRepository(
        apiService: ApiService
    ): GetMovieListRepository {
        return GetMovieListRepositoryImpl(apiService)
    }
}
