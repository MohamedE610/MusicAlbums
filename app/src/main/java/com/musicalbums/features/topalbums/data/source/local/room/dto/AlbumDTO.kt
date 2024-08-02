package com.musicalbums.features.topalbums.data.source.local.room.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.musicalbums.core.room.constant.RoomConstants
import com.musicalbums.features.topalbums.domain.entity.Genre

@Entity(tableName = RoomConstants.TABLE_NAME)
data class AlbumDTO(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("artistName")
    val artistName: String,
    @ColumnInfo("releaseDate")
    val releaseDate: String,
    @ColumnInfo("artworkUrl100")
    val artworkUrl100: String,
    @ColumnInfo("genres")
    val genres: List<Genre>,
    @ColumnInfo("copyright")
    val copyright: String,
    @ColumnInfo("url")
    val url: String
)