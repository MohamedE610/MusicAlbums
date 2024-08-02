package com.musicalbums.features.topalbums.data.mapper

import com.musicalbums.features.topalbums.data.source.local.room.dto.AlbumDTO
import com.musicalbums.features.topalbums.data.source.remote.response.AlbumResponse
import com.musicalbums.features.topalbums.data.source.remote.response.GenreResponse
import com.musicalbums.features.topalbums.domain.entity.Album
import com.musicalbums.features.topalbums.domain.entity.Genre

fun AlbumDTO.toDomain(): Album {
    return Album(
        id = id,
        name = name,
        artistName = artistName,
        releaseDate = releaseDate,
        artworkUrl100 = artworkUrl100,
        genres = genres,
        copyright = copyright,
        url = url
    )
}

fun GenreResponse.toDomain(): Genre {
    return Genre(id, name, url)
}

fun AlbumResponse.toDTO(copyright:String): AlbumDTO {
    return AlbumDTO(
        id = id,
        name = name,
        artistName = artistName,
        releaseDate = releaseDate,
        artworkUrl100 = artworkUrl100,
        genres = genres.map { it.toDomain() },
        copyright = copyright,
        url = url
    )
}