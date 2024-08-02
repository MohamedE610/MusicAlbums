package com.musicalbums.features.topalbums.data.source.local.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class AlbumObj : RealmObject {
    @PrimaryKey
    var _id: String = ""
    var name: String = ""
    var artistName: String = ""
    var releaseDate: String = ""
    var artworkUrl100: String = ""
    var genres: RealmList<GenreObj> = realmListOf()
    var copyright: String = ""
    var url: String = ""
}

class GenreObj : RealmObject {
    var id: String = ""
    var name: String = ""
    var url: String = ""
}
