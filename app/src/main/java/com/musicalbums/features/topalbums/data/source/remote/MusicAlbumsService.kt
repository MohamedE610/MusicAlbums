package com.musicalbums.features.topalbums.data.source.remote

import com.musicalbums.features.topalbums.data.source.remote.response.TopAlbumsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicAlbumsService {
    @GET("api/v2/us/music/most-played/{albums_num}/albums.json")
    suspend fun getTopAlbums(@Path("albums_num") albumsNum: Int = ALBUMS_NUM): TopAlbumsResponse
}

private const val ALBUMS_NUM = 100