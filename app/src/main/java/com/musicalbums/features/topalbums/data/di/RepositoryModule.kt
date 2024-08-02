package com.musicalbums.features.topalbums.data.di

import com.musicalbums.features.topalbums.data.repository.AlbumsRepositoryImpl
import com.musicalbums.features.topalbums.data.source.local.realm.AlbumsRealmDao
import com.musicalbums.features.topalbums.data.source.local.realm.AlbumsRealmDaoImpl
import com.musicalbums.features.topalbums.domain.repository.AlbumsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserDao(impl: AlbumsRealmDaoImpl): AlbumsRealmDao

    @Binds
    @ViewModelScoped
    abstract fun bindAlbumsRepository(impl: AlbumsRepositoryImpl): AlbumsRepository
}