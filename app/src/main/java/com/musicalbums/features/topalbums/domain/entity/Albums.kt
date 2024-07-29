package com.musicalbums.features.topalbums.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Album(
    val id: String,
    val name: String,
    val artistName: String,
    val releaseDate: String,
    val artworkUrl100: String,
    val genres: List<Genre>,
    val copyright: String,
    val url: String,
) : Parcelable


@Parcelize
@Serializable
data class Genre(
    val id: String,
    val name: String,
    val url: String
) : Parcelable