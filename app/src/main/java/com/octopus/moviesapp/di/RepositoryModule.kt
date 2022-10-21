package com.octopus.moviesapp.di

import com.octopus.moviesapp.data.repository.*
import com.octopus.moviesapp.domain.mapper.*
import com.octopus.moviesapp.util.AuthUtils
import com.octopus.moviesapp.util.AuthUtilsImpl
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
    abstract fun bindMoviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl,
    ): MoviesRepository

    @ViewModelScoped
    @Binds
    abstract fun bindTVShowsRepository(
        tvShowsRepositoryImpl: TVShowsRepositoryImpl,
    ): TVShowsRepository

    @ViewModelScoped
    @Binds
    abstract fun bindGenresRepository(
        genresRepositoryImpl: GenresRepositoryImpl,
    ): GenresRepository

    @ViewModelScoped
    @Binds
    abstract fun bindPersonRepository(
        personRepositoryImpl: PersonRepositoryImpl,
    ): PersonRepository

    @ViewModelScoped
    @Binds
    abstract fun bindAccountRepository(
        accountRepositoryImp: AccountRepositoryImp
    ) : AccountRepository

    @ViewModelScoped
    @Binds
    abstract fun bindListsRepository(
        listsRepositoryImp: ListsRepositoryImp
    ) : ListsRepository
}