package com.octopus.moviesapp.di

import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.data.repository.MainRepositoryImpl
import com.octopus.moviesapp.domain.mapper.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService,
        moviesMapper: MoviesMapper,
        tvShowMapper: TVShowMapper,
        genresMapper: GenresMapper,
        castMapper: CastMapper,
        trailerMapper:TrailerMapper
    ): MainRepository {
        return MainRepositoryImpl(apiService, moviesMapper, tvShowMapper, genresMapper, castMapper,trailerMapper)
    }
}