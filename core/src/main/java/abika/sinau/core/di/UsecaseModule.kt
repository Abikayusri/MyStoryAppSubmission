package abika.sinau.core.di

import abika.sinau.core.domain.repository.StoryAppRepository
import abika.sinau.core.domain.usecase.StoryAppUsecase
import abika.sinau.core.domain.usecase.StoryAppUsecaseImpl
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
object UsecaseModule {

    @Provides
    @Singleton
    fun provideUsecase(
        repository: StoryAppRepository
    ): StoryAppUsecase {
        return StoryAppUsecaseImpl(repository)
    }
}