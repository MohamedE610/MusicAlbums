package com.musicalbums.core.di

import android.app.Application
import com.musicalbums.core.room.database.AlbumsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {
    @Provides
    @Singleton
    fun provideAlbumsDatabaseDB(application: Application): AlbumsDatabase {
        return AlbumsDatabase.getInstance(application.applicationContext)
    }
}