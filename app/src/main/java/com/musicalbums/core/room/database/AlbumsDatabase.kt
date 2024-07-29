package com.musicalbums.core.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.musicalbums.core.room.constant.RoomConstants
import com.musicalbums.core.room.converter.AlbumGenreConverter
import com.musicalbums.features.topalbums.data.source.local.dao.AlbumsDao
import com.musicalbums.features.topalbums.data.source.local.dto.AlbumDTO

@Database(
    entities = [AlbumDTO::class],
    version = RoomConstants.DB_VERSION,
    exportSchema = false,
)
@TypeConverters(AlbumGenreConverter::class)
abstract class AlbumsDatabase : RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao

    companion object {
        @Volatile
        private var INSTANCE: AlbumsDatabase? = null

        fun getInstance(context: Context): AlbumsDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AlbumsDatabase::class.java,
            RoomConstants.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}