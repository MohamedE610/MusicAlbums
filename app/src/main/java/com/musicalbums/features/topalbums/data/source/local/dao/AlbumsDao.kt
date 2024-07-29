package com.musicalbums.features.topalbums.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.musicalbums.core.room.constant.RoomConstants
import com.musicalbums.features.topalbums.data.source.local.dto.AlbumDTO
import com.musicalbums.features.topalbums.domain.entity.Album

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM ${RoomConstants.TABLE_NAME}")
    suspend fun getAlbums(): List<AlbumDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbums(list: List<AlbumDTO>)

    @Query("DELETE FROM ${RoomConstants.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${RoomConstants.TABLE_NAME} WHERE id=:id")
    suspend fun getAlbum(id: String): Album?
}