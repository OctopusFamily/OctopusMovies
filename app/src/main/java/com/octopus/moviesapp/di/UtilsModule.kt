package com.octopus.moviesapp.di

import com.octopus.moviesapp.util.AuthUtils
import com.octopus.moviesapp.util.AuthUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UtilsModule {

    @ViewModelScoped
    @Binds
    abstract fun bindAuthUtils(
        authUtilsImpl: AuthUtilsImpl,
    ): AuthUtils
}