package com.musicalbums.features.topalbums.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TopAlbumsResponse(
    @SerializedName("feed")
    val feed: TopAlbumsFeed
)

data class TopAlbumsFeed(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("results")
    val results: List<AlbumResponse>
)

data class AlbumResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("url")
    val url: String
)

data class GenreResponse(
    @SerializedName("genreId")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)