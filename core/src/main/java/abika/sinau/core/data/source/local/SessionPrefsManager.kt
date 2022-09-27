package abika.sinau.core.data.source.local

import abika.sinau.core.utils.BindingSessionPrefs
import abika.sinau.core.utils.StoryConst.PREFS_USER_TOKEN
import android.content.SharedPreferences


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
class SessionPrefsManager(@BindingSessionPrefs private val preferences: SharedPreferences) {
    var userToken: String
        get() = preferences.getString(PREFS_USER_TOKEN, "") ?: ""
        set(value) {
            preferences.edit().putString(PREFS_USER_TOKEN, value).apply()
        }

    fun clearUserToken() {
        preferences.edit().apply {
            clear()
            apply()
        }
    }
}