package com.musicalbums.features.topalbums.data.repository

import com.musicalbums.core.di.Dispatcher
import com.musicalbums.core.di.DispatcherKey
import com.musicalbums.core.extension.emitFlow
import com.musicalbums.core.extension.onSuccess
import com.musicalbums.features.topalbums.data.mapper.toDTO
import com.musicalbums.features.topalbums.data.mapper.toDomain
import com.musicalbums.features.topalbums.data.source.local.dao.AlbumsDao
import com.musicalbums.features.topalbums.data.source.remote.MusicAlbumsService
import com.musicalbums.features.topalbums.domain.entity.Album
import com.musicalbums.features.topalbums.domain.repository.AlbumsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val localeDataSource: AlbumsDao,
    private val remoteDataSource: MusicAlbumsService,
    @Dispatcher(DispatcherKey.IO) private val dispatcher: CoroutineDispatcher
) : AlbumsRepository {
    override fun getTopAlbums(): Flow<List<Album>> {
        return emitFlow {
            val list = localeDataSource.getAlbums().map { it.toDomain() }
            list.ifEmpty { syncAlbumsData() }
        }.onSuccess {
            syncAlbumsData()
        }.flowOn(dispatcher)
    }

    override fun getAlbum(id: String): Flow<Album?> {
        return emitFlow { localeDataSource.getAlbum(id) }
    }

    private suspend fun syncAlbumsData(): List<Album> {
        val response = remoteDataSource.getTopAlbums()
        val list = response.feed.results.map { it.toDTO(response.feed.copyright) }
        localeDataSource.deleteAll()
        localeDataSource.saveAlbums(list)
        return list.map { it.toDomain() }
    }
}