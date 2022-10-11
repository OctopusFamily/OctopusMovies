package com.octopus.moviesapp.di

import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.mapper.GenresMapper
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.TVShowMapper
import com.octopus.moviesapp.data.repository.MainRepository
import com.octopus.moviesapp.data.repository.MainRepositoryImpl
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
    ): MainRepository {
        return MainRepositoryImpl(apiService, moviesMapper, tvShowMapper, genresMapper)
    }
}