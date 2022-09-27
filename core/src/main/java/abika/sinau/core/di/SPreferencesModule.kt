package abika.sinau.core.di

import abika.sinau.core.data.source.local.SessionPrefsManager
import abika.sinau.core.utils.BindingSessionPrefs
import abika.sinau.core.utils.SessionPrefs
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */

@Module
@InstallIn(SingletonComponent::class)
object SPreferencesModule {

    const val PREFS_SESSION = "PREFS_SESSION"

    @Provides
    @Singleton
    @BindingSessionPrefs
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_SESSION, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    @SessionPrefs
    fun provideSessionPrefsManager(@BindingSessionPrefs preferences: SharedPreferences): SessionPrefsManager {
        return SessionPrefsManager(preferences)
    }
}