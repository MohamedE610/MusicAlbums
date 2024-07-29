package com.musicalbums.navigation

sealed class AppScreen(val path: String) {
    data object TopAlbums : AppScreen(path = "top/albums")
    data object AlbumDetails : AppScreen(path = "album/{id}")
}