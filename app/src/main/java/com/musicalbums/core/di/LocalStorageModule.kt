package com.musicalbums.core.di

import android.app.Application
import com.musicalbums.core.room.database.AlbumsDatabase
import com.musicalbums.features.topalbums.data.source.local.realm.AlbumObj
import com.musicalbums.features.topalbums.data.source.local.realm.GenreObj
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {
    @Provides
    @Singleton
    fun provideAlbumsDatabaseDB(application: Application): AlbumsDatabase {
        return AlbumsDatabase.getInstance(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideAlbumsRealmDBInstance(application: Application): Realm {
        val realmConfig =
            RealmConfiguration.create(schema = setOf(AlbumObj::class, GenreObj::class))
        return Realm.open(realmConfig)
    }
}