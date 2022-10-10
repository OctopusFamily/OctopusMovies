package com.octopus.moviesapp.di

import androidx.room.ProvidedAutoMigrationSpec
import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.mapper.GenresMapper
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
        tvShowMapper: TVShowMapper,
        genresMapper: GenresMapper,
    ): MainRepository {
        return MainRepositoryImpl(apiService, moviesMapper, tvShowMapper, genresMapper)
    }

    @Singleton
    @Provides
    fun provideMoviesMapper(): MoviesMapper {
        return MoviesMapper()
    }

    @Singleton
    @Provides
    fun provideTvShowsMapper(): TVShowMapper {
        return TVShowMapper()
    }

    @Singleton
    @Provides
    fun provideGenresMapper(): GenresMapper {
        return GenresMapper()
    }

}