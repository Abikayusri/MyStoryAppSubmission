package abika.sinau.core.data.source.local

import abika.sinau.core.utils.BindingSessionPrefs
import abika.sinau.core.utils.StoryConst.PREFS_HAS_LOGIN
import abika.sinau.core.utils.StoryConst.PREFS_USER_NAME
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

    var hasLogin: Boolean
        get() = preferences.getBoolean(PREFS_HAS_LOGIN, false)
        set(value) {
            preferences.edit().putBoolean(PREFS_HAS_LOGIN, value).apply()
        }

    var userName: String
        get() = preferences.getString(PREFS_USER_NAME, "") ?: ""
        set(value) {
            preferences.edit().putString(PREFS_USER_NAME, value).apply()
        }

    fun clearUserToken() {
        preferences.edit().apply {
            clear()
            apply()
        }
    }
}