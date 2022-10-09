package com.octopus.moviesapp.di

import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.TVShowMapper
import com.octopus.moviesapp.domain.repository.MainRepository
import com.octopus.moviesapp.domain.repository.MainRepositoryImpl
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
        tvShowsMapper: TVShowMapper
    ): MainRepository {
        return MainRepositoryImpl(apiService, moviesMapper, tvShowsMapper)
    }

    @Singleton
    @Provides
    fun provideMoviesMapper(): MoviesMapper {
        return MoviesMapper()
    }

    @Singleton
    @Provides
    fun provideTVShowsMapper(): TVShowMapper {
        return TVShowMapper()
    }
}