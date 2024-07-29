package com.musicalbums.features.topalbums.domain.usecase

import com.musicalbums.core.base.BaseUseCaseWithParam
import com.musicalbums.features.topalbums.domain.entity.Album
import com.musicalbums.features.topalbums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(
    private val repository: AlbumsRepository
) : BaseUseCaseWithParam<String, Album?>() {
    override fun invoke(param: String): Flow<Album?> {
        return repository.getAlbum(id = param)
    }
}