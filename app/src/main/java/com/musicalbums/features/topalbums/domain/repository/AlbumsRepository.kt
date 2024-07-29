package com.musicalbums.features.topalbums.domain.repository

import com.musicalbums.features.topalbums.domain.entity.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun getTopAlbums(): Flow<List<Album>>
    fun getAlbum(id: String): Flow<Album?>
}