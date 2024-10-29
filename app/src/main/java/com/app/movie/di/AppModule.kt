package com.app.movie.di

import com.app.movie.data.remote.ApiService
import com.app.movie.data.repository.GetCharacterRepositoryImpl
import com.app.movie.data.repository.GetMovieListRepositoryImpl
import com.app.movie.data.repository.GetQuoteRepositoryImpl
import com.app.movie.domain.repository.GetCharacterRepository
import com.app.movie.domain.repository.GetMovieListRepository
import com.app.movie.domain.repository.GetQuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService.create()
    }

    @Provides
    @Singleton
    fun provideGetMovieListRepository(
        apiService: ApiService
    ): GetMovieListRepository {
        return GetMovieListRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetCharacterRepository(
        apiService: ApiService
    ): GetCharacterRepository {
        return GetCharacterRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetQuoteRepository(
        apiService: ApiService
    ): GetQuoteRepository {
        return GetQuoteRepositoryImpl(apiService)
    }
}
