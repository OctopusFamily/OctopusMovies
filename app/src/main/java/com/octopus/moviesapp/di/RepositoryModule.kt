package com.octopus.moviesapp.di

import com.octopus.moviesapp.data.repository.account.AccountRepository
import com.octopus.moviesapp.data.repository.account.AccountRepositoryImp
import com.octopus.moviesapp.data.repository.genres.GenresRepository
import com.octopus.moviesapp.data.repository.genres.GenresRepositoryImpl
import com.octopus.moviesapp.data.repository.home.HomeRepository
import com.octopus.moviesapp.data.repository.home.HomeRepositoryImpl
import com.octopus.moviesapp.data.repository.lists.ListsRepository
import com.octopus.moviesapp.data.repository.lists.ListsRepositoryImp
import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.data.repository.movies.MoviesRepositoryImpl
import com.octopus.moviesapp.data.repository.person.PersonRepository
import com.octopus.moviesapp.data.repository.person.PersonRepositoryImpl
import com.octopus.moviesapp.data.repository.search.SearchRepository
import com.octopus.moviesapp.data.repository.search.SearchRepositoryImpl
import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

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
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl,
    ) : HomeRepository

    @ViewModelScoped
    @Binds
    abstract fun bindListsRepository(
        listsRepositoryImp: ListsRepositoryImp
    ) : ListsRepository

    @ViewModelScoped
    @Binds
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ) : SearchRepository
}