package abika.sinau.core.di

import abika.sinau.core.data.source.local.db.StoryDatabase
import abika.sinau.core.data.source.remote.DataSource
import abika.sinau.core.data.source.remote.DataSourceImpl
import abika.sinau.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService, storyDatabase: StoryDatabase): DataSource {
        return DataSourceImpl(apiService, storyDatabase)
    }
}