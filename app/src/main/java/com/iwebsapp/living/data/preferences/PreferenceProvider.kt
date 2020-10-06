package com.iwebsapp.living.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSaveAt(saveAt: String) {
        preference.edit().putString(
            KEY_SAVED_AT,
            saveAt
        ).apply()
    }

    fun getLastSaveAt(): String? {
        return preference.getString(KEY_SAVED_AT, null)
    }

}