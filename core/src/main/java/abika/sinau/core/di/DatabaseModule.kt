package abika.sinau.core.di

import abika.sinau.core.data.source.local.db.RemoteKeysDao
import abika.sinau.core.data.source.local.db.StoryDao
import abika.sinau.core.data.source.local.db.StoryDatabase
import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author by Abika Chairul Yusri on 10/28/2022
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideStoryDatabase(application: Application): StoryDatabase {
        return Room.databaseBuilder(application, StoryDatabase::class.java, "story_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideStoryDAO(storyDatabase: StoryDatabase): StoryDao {
        return storyDatabase.storyDao()
    }

    @Provides
    @Singleton
    fun provideRemoteKeysDAO(storyDatabase: StoryDatabase): RemoteKeysDao {
        return storyDatabase.remoteKeysDao()
    }
}