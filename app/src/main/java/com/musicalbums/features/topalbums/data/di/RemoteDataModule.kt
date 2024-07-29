package com.musicalbums.features.topalbums.data.di

import com.musicalbums.features.topalbums.data.source.remote.MusicAlbumsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object RemoteDataModule {
    @Provides
    @ViewModelScoped
    fun providesRemoteDataSource(retrofit: Retrofit): MusicAlbumsService {
        return retrofit.create(MusicAlbumsService::class.java)
    }
}