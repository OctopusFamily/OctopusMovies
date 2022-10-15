package com.octopus.moviesapp.di

import com.octopus.moviesapp.data.repository.*
import com.octopus.moviesapp.domain.mapper.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun provideMoviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl,
    ): MoviesRepository

    @ViewModelScoped
    @Binds
    abstract fun provideTVShowsRepository(
        tvShowsRepositoryImpl: TVShowsRepositoryImpl,
    ): TVShowsRepository

    @ViewModelScoped
    @Binds
    abstract fun provideGenresRepository(
        genresRepositoryImpl: GenresRepositoryImpl,
    ): GenresRepository
}