package com.musicalbums.features.topalbums.domain.usecase

import com.musicalbums.core.base.BaseUseCase
import com.musicalbums.features.topalbums.domain.entity.Album
import com.musicalbums.features.topalbums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAlbumsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) : BaseUseCase<List<Album>>() {
    override fun invoke(): Flow<List<Album>> {
        return repository.getTopAlbums()
    }
}