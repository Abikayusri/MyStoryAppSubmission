package abika.sinau.core.data.source.local.db

import abika.sinau.core.data.source.local.entity.RemoteKeysEntity
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * @author by Abika Chairul Yusri on 10/28/2022
 */

@Database(
    entities = [StoryListResponse::class, RemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)

abstract class StoryDatabase: RoomDatabase() {

    abstract fun storyDao(): StoryDao
    abstract fun remoteKeysDao(): RemoteKeysDao

}