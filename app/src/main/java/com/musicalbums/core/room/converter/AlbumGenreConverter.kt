package com.musicalbums.core.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.musicalbums.features.topalbums.domain.entity.Genre

class AlbumGenreConverter {
    @TypeConverter
    fun toDatabaseValue(genreList: List<Genre>): String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre> {
        val genreListType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(value, genreListType)
    }
}