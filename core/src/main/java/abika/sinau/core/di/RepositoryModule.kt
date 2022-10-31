package abika.sinau.core.di

import abika.sinau.core.data.source.StoryAppRepositoryImpl
import abika.sinau.core.data.source.DataSource
import abika.sinau.core.domain.repository.StoryAppRepository
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
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        dataSource: DataSource
    ): StoryAppRepository {
        return StoryAppRepositoryImpl(dataSource)
    }
}