package com.musicalbums.features.topalbums.data.source.local.realm

import io.realm.kotlin.Realm
import javax.inject.Inject
import kotlin.reflect.KClass

interface AlbumsRealmDao : RealmDao<AlbumObj>

class AlbumsRealmDaoImpl @Inject constructor(
    realmInstance: Realm
) : AlbumsRealmDao {
    override val realm: Realm = realmInstance
    override val clazz: KClass<AlbumObj> = AlbumObj::class
}
