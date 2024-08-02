package com.musicalbums.features.topalbums.data.mapper

import com.musicalbums.features.topalbums.data.source.local.realm.AlbumObj
import com.musicalbums.features.topalbums.data.source.local.realm.GenreObj
import com.musicalbums.features.topalbums.data.source.local.room.dto.AlbumDTO
import com.musicalbums.features.topalbums.data.source.remote.response.AlbumResponse
import com.musicalbums.features.topalbums.data.source.remote.response.GenreResponse
import com.musicalbums.features.topalbums.domain.entity.Album
import com.musicalbums.features.topalbums.domain.entity.Genre
import io.realm.kotlin.ext.realmListOf

fun AlbumObj.toDomain(): Album {
    return Album(
        id = _id,
        name = name,
        artistName = artistName,
        releaseDate = releaseDate,
        artworkUrl100 = artworkUrl100,
        genres = genres.map { it.toDomain() },
        copyright = copyright,
        url = url
    )
}

fun GenreObj.toDomain(): Genre {
    return Genre(id, name, url)
}

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

fun AlbumResponse.toDTO(copyright: String): AlbumDTO {
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

fun AlbumResponse.toRealmObj(copyright: String): AlbumObj {
    return AlbumObj().apply {
        _id = this@toRealmObj.id
        name = this@toRealmObj.name
        artistName = this@toRealmObj.artistName
        releaseDate = this@toRealmObj.releaseDate
        artworkUrl100 = this@toRealmObj.artworkUrl100
        genres = realmListOf<GenreObj>().apply {
            addAll(this@toRealmObj.genres.map { it.toRealmObj() })
        }
        this.copyright = copyright
        url = this@toRealmObj.url
    }
}

fun GenreResponse.toRealmObj(): GenreObj {
    return GenreObj().apply {
        id = this@toRealmObj.id
        name = this@toRealmObj.name
        url = this@toRealmObj.url
    }
}