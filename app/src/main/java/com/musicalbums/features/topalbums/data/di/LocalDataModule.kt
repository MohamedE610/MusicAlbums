package com.musicalbums.features.topalbums.data.di

import com.musicalbums.core.room.database.AlbumsDatabase
import com.musicalbums.features.topalbums.data.source.local.room.dao.AlbumsDao
import com.musicalbums.features.topalbums.data.source.remote.MusicAlbumsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object LocalDataModule {
    @Provides
    @ViewModelScoped
    fun providesLocalDataSource(database: AlbumsDatabase): AlbumsDao {
        return database.albumsDao()
    }
}